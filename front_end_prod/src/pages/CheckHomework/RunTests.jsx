/* ------------------------------------------------------------------------------------------------ */
/* ---- React Page Component - Run Tests ---------------------------------------------------------- */

import { useContext, useEffect, useState } from 'react';
import FilesContext from '../../context/FilesContext';

import { useNavigate, Link } from 'react-router-dom';

import { useMantineTheme, Anchor, Text } from '@mantine/core';
import { showNotification } from '@mantine/notifications';
import { useInterval } from '@mantine/hooks';

import { AlertCircle } from 'tabler-icons-react';

import { FileIcon, defaultStyles } from 'react-file-icon';

import useAxiosFetch from '../../hooks/useAxiosFetch';

import { Loader, Section } from '../../components';

import { formatFileSize } from '../../utils';


export const RunTests = ({ nextStep }) => {
    // 
    const timerInitialValue = 5;
    let showedNotification = false;

    // 
    const navigate = useNavigate();
    const { files, setFiles, setTestsData } = useContext(FilesContext);
    
    // 
    const fileNameNoExt = files[0].name.split('.')[0];
    const path = `homework/deploy/${fileNameNoExt}/TestResults.json`;
    const { data, error, isLoading } = callApi(path);

    // !!!@@ yair changes delete   V when have a server
    // const { data, error, isLoading } = callApi('src/api/TestResults.json');
    // !!!@@ yair changes delete ^ when have a server
    // 

    const [loadingSpinner, setLoadingSpinner] = useState(true);
    const [timerSeconds, setTimerSeconds] = useState(timerInitialValue);
    const timer = useInterval(() => setTimerSeconds((s) => s - 1), 1000);

    const navigateToTestsResults = () => {
        timer.stop();
        setTestsData(data);
        console.log("DATA:" + data);
        nextStep();
    };

    const goBackToHome = () => {
        timer.stop();
        setFiles([]);
        navigate('/');
    };

    useEffect(() => {
        if (files.length === 0) {
            navigate('/');
        }
    }, [files]);

    useEffect(() => {
        if (!isLoading && loadingSpinner) {
            setTimeout(() => {
                setLoadingSpinner(false);
            }, Math.random() * 1000 + 1000);
        }
        return timer.stop;
    }, [isLoading]);

    useEffect(() => {
        if (!loadingSpinner && error && !showedNotification) {
            showNotification({
                title: 'Something went wrong',
                message:
                    error.message || 'An error occurred while fetching the data. please try again later.',
                color: 'red',
                autoClose: 5000,
            });
            showedNotification = true;
        }
    }, [loadingSpinner]);

    useEffect(() => {
        if (!error && !loadingSpinner && !timer.active) {
            timer.start();
        }
    }, [loadingSpinner, timer]);

    useEffect(() => {
        if (timerSeconds <= 0) {
            timer.stop();
            setTimeout(() => {
                navigateToTestsResults();
            }, 500);
        }
    }, [timerSeconds]);

    return (
        <div className='RunTests'>
            {files.length <= 0 ? (
                <NoFiles goBack={goBackToHome} />
            ) : loadingSpinner ? (
                <LoadingData files={files} />
            ) : error ? (
                <ErrorAccrued files={files} goBack={goBackToHome} />
            ) : (
                <TestFinishedSuccessfully files={files} movePage={navigateToTestsResults} seconds={timerSeconds} />
            )}
        </div>
    );
};

// ---- Helper functions ------------------------

const callApi = (path) => {
    console.log("PATH IS " + path)
    const res = useAxiosFetch(path);
    return res;
};

// ---- Inner components ------------------------

const FileDetails = ({ file }) => {
    const theme = useMantineTheme();
    const extension = file.name.split('.').pop();

    return (
        <div className='FileDetails'>
            <div className='FileDetails-icon'>
                <FileIcon
                    extension={extension}
                    labelTextColor={
                        theme.colorScheme === 'dark' ? theme.colors.dark[6] : theme.colors.gray[0]
                    }
                    type='image'
                    {...defaultStyles[extension]}
                />
            </div>
            <div className='FileDetails-data'>
                <Text className='FileDetails-name'>{file.name}</Text>
                <Text className='FileDetails-size' color='dimmed'>
                    {formatFileSize(file.size)}
                </Text>
            </div>
        </div>
    );
};

const NoFiles = ({ goBack }) => (
    <Section>
        <Text align='center' mb='lg' color='dimmed' size='md' weight='bold'>
            No files have been uploaded yet.
            <br />
            Please upload a file before you try to run any tests.
            <br />
            <br />
            Please go back to the{' '}
            <Anchor className='action-link' component={Link} to='/' mt='sm' onClick={goBack}>
                Upload Page (Home)
            </Anchor>
            .
        </Text>
    </Section>
);

const LoadingData = ({ files }) => (
    <>
        <Section>
            <Text align='center' mb='lg' color='dimmed' size='md' weight='bold'>
                Currently running tests for the file:
            </Text>
            {files.map((file, i) => (
                <FileDetails key={i} file={file} />
            ))}
            <br />
            <br />
            <Loader />
            <br />
            <br />
        </Section>
        <Section>
            <Text align='center' mb='lg' color='dimmed' size='md' weight='bold'>
                This process may take several minutes, please be patient.
            </Text>
        </Section>
    </>
);

const ErrorAccrued = ({ files, goBack }) => (
    <>
        <Section>
            <Text align='center' mb='lg' color='dimmed' size='md' weight='bold'>
                Something went wrong while running tests for the file:
            </Text>
            {files.map((file, i) => (
                <FileDetails key={i} file={file} />
            ))}
            <br />
            <AlertCircle className='error-icon' />
        </Section>
        <Section style={{ textAlign: 'center', marginTop: '2rem' }}>
            <Anchor
                className='action-link'
                component={Link}
                to='/'
                align='center'
                size='md'
                weight='bold'
                onClick={goBack}
            >
                Click here to go back and try again.
            </Anchor>
            <Text align='center' color='dimmed' size='md' weight='bold'>
                If this error persists, please contact support.
            </Text>
        </Section>
    </>
);

const TestFinishedSuccessfully = ({ files, movePage, seconds }) => (
    <>
        <Section>
            <Text align='center' mb='lg' color='dimmed' size='md' weight='bold'>
                Finished running tests for the file:
            </Text>
            {files.map((file, i) => (
                <FileDetails key={i} file={file} />
            ))}
        </Section>
        <Section style={{ textAlign: 'center', marginTop: '2rem' }}>
            <Anchor className='action-link' align='center' size='md' weight='bold' onClick={movePage}>
                Click here to view the tests results.
            </Anchor>
            <Text align='center' color='dimmed' size='md' weight='bold'>
                You will be automatically redirected to the next page in {seconds} seconds.
            </Text>
        </Section>
    </>
);

export default RunTests;

/* ------------------------------------------------------------------------------------------------ */
/* ------------------------------------------------------------------------------------------------ */
