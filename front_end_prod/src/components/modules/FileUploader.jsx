/* ------------------------------------------------------------------------------------------------ */
/* ---- React Component - FileUploader ------------------------------------------------------------ */
import axios from 'axios';

import { useState, useContext } from 'react';
import FilesContext from '../../context/FilesContext';
import { useNavigate } from 'react-router-dom';

import { Box, ActionIcon, Paper, Text, Anchor, useMantineTheme, Button, Group } from '@mantine/core';
import { MIME_TYPES, Dropzone } from '@mantine/dropzone';
import { showNotification } from '@mantine/notifications';

import { CloudUpload, Files, X, Trash, ChevronRight } from 'tabler-icons-react';
import { FileIcon, defaultStyles } from 'react-file-icon';

import { Tooltip } from '..';

import { formatFileSize } from '../../utils';

export const FileUploader = () => {
    const MB = 1024 ** 2;

    const navigate = useNavigate();
    const theme = useMantineTheme();
    const { files, setFiles } = useContext(FilesContext);

    const handleDrop = (acceptedFiles) => {
        setFiles(acceptedFiles);
        showNotification({
            color: 'green',
            title: 'File Uploaded',
            message: 'Your file have been uploaded successfully.',
        });
        console.log(files);
    };
    const handleReject = (rejectedFiles) => {
        showNotification({
            color: 'red',
            title: rejectedFiles[0]?.errors[0]?.code
                ? rejectedFiles[0].errors[0].code
                    .split('-')
                    .map((word) => word[0].toUpperCase() + word.slice(1))
                    .join(' ')
                : 'Something went wrong',
            message: rejectedFiles[0]?.errors[0]?.message
                ? rejectedFiles[0].errors[0].message.includes('File is larger than')
                    ? rejectedFiles[0].errors[0].message
                        .split(' ')
                        .map((word) => (+word ? formatFileSize(word) : word))
                        .join(' ')
                    : rejectedFiles[0].errors[0].message
                : 'Please try again later.',
        });
    };

    const checkHomework = () => {
        // !!!@@ yair changes delete V when have a server
        // navigate('/check-homework');
        // return;
        // !!!@@ yair changes delete ^ when have a server
        

        const bodyFormData = new FormData();
        bodyFormData.append('checkHomework', "Check Homework");
        bodyFormData.append('fileName', files[0]);

        axios({
            method: "post",
            url: 'form.py',
            data: bodyFormData,
            headers: { "Content-Type": "multipart/form-data" },
        })
        .then( (response) => {
            //handle success
            console.log(response);
            navigate('/check-homework');
        })
        .catch( (response) => {
            //handle error
            console.log(response);
            navigate('/');
        });
    };

    return (
        <>
            <Paper className='FileUploader Main-Paper' radius='xl' shadow='lg' p='xl'>
                <Dropzone
                    multiple={false}
                    accept={['.zip', MIME_TYPES.zip]}
                    onDrop={handleDrop}
                    onReject={handleReject}
                    maxSize={50 * MB}
                    radius='lg'
                    p='xl'
                    styles={{
                        reject: {
                            backgroundColor: '#4caf5010',
                        },
                    }}
                >
                    {(status) => DropzoneInner(status, theme)}
                </Dropzone>
                {files.length > 0 && (
                    <Group component='ul' mt='2xl' direction='column' position='center' spacing='lg'>
                        <PreviewFile files={files} setFiles={setFiles} key={files[0].name} file={files[0]} />
                        <Button
                            className='check-homework-btn'
                            rightIcon={<ChevronRight size='1.2em' />}
                            onClick={checkHomework}
                        >
                            Check Homework
                        </Button>
                    </Group>
                )}
            </Paper>
        </>
    );
};

// ---- Helper functions ------------------------

const getStatusColor = (status, theme) => {
    return status.accepted || status.rejected
        ? theme.colors[theme.primaryColor][theme.colorScheme === 'dark' ? 4 : 6]
        : status.rejected
            ? theme.colors.red[theme.colorScheme === 'dark' ? 4 : 6]
            : theme.colorScheme === 'dark'
                ? theme.colors.dark[0]
                : theme.colors.gray[7];
};

// ---- Inner components ------------------------

const ImageUploadIcon = ({ status, ...props }) => {
    if (status.accepted || status.rejected) {
        return <CloudUpload {...props} />;
    }

    if (status.rejected) {
        return <X {...props} />;
    }

    return <Files {...props} />;
};

const DropzoneInner = (status, theme) => (
    <div className='dropzone-inner'>
        <ImageUploadIcon
            status={status}
            style={{ color: getStatusColor(status, theme), strokeWidth: '1.5' }}
            size='6rem'
        />
        <Text size='lg' align='center' mb='-0.5rem'>
            Drag & Drop Your Files Here To Upload
        </Text>
        <Text size='xs' color='dimmed' align='center' mb='2rem'>
            Attach one zip format file, your file should not exceed 50mb
        </Text>
        <Group position='center'>
            <Text size='sm' mr='-0.3rem' align='center'>
                Or click here to
            </Text>
            <Anchor variant='link'>Browse Files</Anchor>
        </Group>
    </div>
);

const PreviewFile = ({ file: currentFile, files, setFiles, ...props }) => {
    const theme = useMantineTheme();
    const extension = currentFile.name.split('.').pop();

    const removeFile = () => {
        const newFiles = files.filter((file) => file.name !== currentFile.name);
        setFiles(newFiles);
    };

    return (
        <Paper className='preview-file' component='li' p='sm' radius='lg' {...props}>
            <Group className='preview-file-inner' position='apart'>
                <Box className='file-data' component='span'>
                    <span className='file-data-icon'>
                        <FileIcon
                            extension={extension}
                            labelTextColor={
                                theme.colorScheme === 'dark' ? theme.colors.dark[6] : theme.colors.gray[0]
                            }
                            type='image'
                            {...defaultStyles[extension]}
                        />
                    </span>
                    <Group
                        direction='column'
                        spacing='0'
                        sx={{ display: 'inline-flex', maxWidth: '75%', overflow: 'auto' }}
                    >
                        <Text size='sm' sx={(theme) => ({ fontWeight: theme.other.fontWeights.bold })}>
                            {currentFile.name}
                        </Text>
                        <Text size='xs' color='dimmed'>
                            {formatFileSize(currentFile.size, 3)}
                        </Text>
                    </Group>
                </Box>
                <Group spacing='0'>
                    <Tooltip tip='Remove file' size='sm'>
                        <ActionIcon onClick={removeFile} color='red' size='2.25rem'>
                            <Trash size='1.2rem' />
                        </ActionIcon>
                    </Tooltip>
                </Group>
            </Group>
        </Paper>
    );
};

export default FileUploader;

/* ------------------------------------------------------------------------------------------------ */
/* ------------------------------------------------------------------------------------------------ */
