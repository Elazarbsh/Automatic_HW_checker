/* ------------------------------------------------------------------------------------------------ */
/* ---- React Page Component - Tests Results ------------------------------------------------------ */

import { useContext, useEffect, useState, useMemo, useCallback } from 'react';
import FilesContext from '../../context/FilesContext';
import { Link } from 'react-router-dom';

import Editor, { DiffEditor, useMonaco, loader } from '@monaco-editor/react';

import ReactDiffViewer, { DiffMethod } from 'react-diff-viewer';

import {
    useMantineColorScheme,
    Accordion,
    Group,
    ActionIcon,
    Title,
    Text,
    Button,
    Modal,
} from '@mantine/core';
import { showNotification } from '@mantine/notifications';
import { useInterval } from '@mantine/hooks';

import { ChevronDown, Report, Vocabulary, Code, Edit } from 'tabler-icons-react';

import { FileIcon, defaultStyles } from 'react-file-icon';

import useAxiosFetch from '../../hooks/useAxiosFetch';

import { Tooltip, Section, FileTree } from '../../components';

import { CUSTOM_DIFF_STYLE } from '../../utilities';

import oldJSON from '../../../api/output/old.json';
import newJSON from '../../../api/output/new.json';

// import homework from '../../../api/36_Hw2_312238629.zip';

export const TestsResults = () => {
    const { testsData, setTestsData } = useContext(FilesContext);
    const [openCode, setOpenCode] = useState(false);

    return (
        <div className='TestsResults'>
            <div className='overall-summary'>
                <Section>
                    <Text align='center' mb='lg' color='dimmed' size='xl' weight='bold'>
                        Overall Summary:
                    </Text>
                </Section>
                <Section className='overall-summary-content'>
                    {testsData.header.map((line, i) => (
                        <>
                            <Text>
                                <strong>
                                    {i === 0
                                        ? 'Course: '
                                        : i === 1
                                        ? 'Assignment: '
                                        : i === 2
                                        ? 'Tests Input: '
                                        : ''}
                                </strong>
                            </Text>
                            <Text component='p' mt='0'>
                                {line}
                            </Text>
                        </>
                    ))}
                    <Text>
                        <strong>Summary:</strong>
                    </Text>
                    {testsData.summary.map((line) => (
                        <Text>{line}</Text>
                    ))}

                    <Button onClick={() => setOpenCode(true)}>
                        Edit Your Files
                        <Edit />
                    </Button>
                </Section>
            </div>
            <Section>
                <Text align='center' mb='lg' color='dimmed' size='xl' weight='bold'>
                    Tests Results:
                </Text>
            </Section>
            <Section>
                <Accordion className='results-accordion' icon={<ChevronDown />} iconPosition='right' multiple>
                    {Object.keys(testsData.tests).map((key) =>
                        createAccordionItem(key, testsData.tests[key])
                    )}
                </Accordion>
            </Section>
            <Modal
                opened={openCode}
                onClose={() => setOpenCode(false)}
                centered
                overflow='inside'
                radius='lg'
                size='95vw'
                styles={{
                    body: {
                        height: '90vh',
                    },
                }}
                title='Code'
            >
                <Group align='flex-start'>
                    <FileTree style={{ width: '20vw' }}>
                        <FileTree.Folder name='src'>
                            <FileTree.Folder name='components'>
                                <FileTree.File name='modules.js'>
                                    <FileTree.File name='FileTree.jsx' />
                                </FileTree.File>
                                <FileTree.File name='FileTree.jsx' />
                            </FileTree.Folder>
                            <FileTree.File name='index.jsx' />
                        </FileTree.Folder>
                        <FileTree.File name='index.jsx' />
                    </FileTree>
                    <Editor
                        height='80vh'
                        width='70vw'
                        defaultLanguage='javascript'
                        defaultValue='// some comment'
                    />
                </Group>
            </Modal>
        </div>
    );
};

// ---- Helper functions ------------------------

const createAccordionItem = (key, test) => {
    const { colorScheme } = useMantineColorScheme();

    const [view, setView] = useState('summary');

    const testNumber = key;
    const failed = test.result.toLowerCase().includes('failed');
    const result = test.result;
    const grade = test.grade;
    const output = {
        current: JSON.stringify(newJSON, undefined, 4),
        excepted: JSON.stringify(oldJSON, undefined, 4),
    };

    return (
        <Accordion.Item
            key={key}
            label={
                <>
                    <Group noWrap position='apart'>
                        <Group>
                            <div className={`accordion-item-number${failed ? ' failed' : ''}`}>
                                {testNumber}
                            </div>
                            <div className='accordion-item-title'>
                                <Text>{result}</Text>
                                <Text size='sm' color='dimmed' weight={400}>
                                    {grade}
                                </Text>
                            </div>
                        </Group>
                        <form
                            className='accordion-item-btns'
                            onClick={(e) => {
                                e.stopPropagation();
                            }}
                            onMouseOver={(e) => {
                                e.preventDefault();
                                e.stopPropagation();
                            }}
                            onChange={(e) => {
                                setView(e.target.value);
                            }}
                        >
                            <input
                                type='radio'
                                name={`view-${testNumber}`}
                                id={`summary-${testNumber}`}
                                value='summary'
                                hidden
                                defaultChecked
                            />
                            <label htmlFor={`summary-${testNumber}`}>
                                <Tooltip tip='View test summary'>
                                    <ActionIcon component='div' variant='hover' size='xl'>
                                        <Report />
                                    </ActionIcon>
                                </Tooltip>
                            </label>
                            <input
                                type='radio'
                                name={`view-${testNumber}`}
                                id={`output-${testNumber}`}
                                value='output'
                                hidden
                            />
                            <label htmlFor={`output-${testNumber}`}>
                                <Tooltip tip='View full output'>
                                    <ActionIcon component='div' variant='hover' size='xl'>
                                        <Vocabulary />
                                    </ActionIcon>
                                </Tooltip>
                            </label>
                        </form>
                    </Group>
                </>
            }
        >
            {view === 'summary' ? (
                <>
                    <Title order={4} align='center' pb='md'>
                        Test Summary
                    </Title>
                    <Text>
                        Lorem ipsum, dolor sit amet consectetur adipisicing elit. Voluptatibus, provident?
                    </Text>
                    <Text>
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Minus itaque odit, quasi
                        dolore at placeat?
                    </Text>
                    <Text>Lorem ipsum dolor sit amet consectetur adipisicing.</Text>
                </>
            ) : view === 'output' ? (
                <>
                    <Title order={4} align='center'>
                        Test Output
                    </Title>
                    {/* <ReactDiffViewer
                        className='diff-viewer'
                        oldValue={output.excepted}
                        newValue={output.current}
                        splitView={true}
                        compareMethod={DiffMethod.CHARS}
                        styles={CUSTOM_DIFF_STYLE}
                        leftTitle='Excepted Output'
                        rightTitle='Your Output'
                        useDarkTheme={colorScheme === 'dark'}
                        language='text/plain'
                    /> */}
                    <DiffEditor
                        original={output.excepted}
                        modified={output.current}
                        theme={colorScheme === 'dark' ? 'vs-dark' : 'light'}
                        height='70vh'
                        language='text/plain'
                    />
                </>
            ) : (
                <></>
            )}
        </Accordion.Item>
    );
};

export default TestsResults;

/* ------------------------------------------------------------------------------------------------ */
/* ------------------------------------------------------------------------------------------------ */
