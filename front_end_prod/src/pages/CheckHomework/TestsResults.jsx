/* ------------------------------------------------------------------------------------------------ */
/* ---- React Page Component - Tests Results ------------------------------------------------------ */

import { useContext, useEffect, useState, useMemo, useCallback } from 'react';
import FilesContext from '../../context/FilesContext';
import axios from 'axios';

import Editor, { DiffEditor, useMonaco, loader } from '@monaco-editor/react';

import {
    useMantineColorScheme,
    Accordion,
    Group,
    ActionIcon,
    Title,
    Text,
    Button,
    Modal,
    Code,
} from '@mantine/core';
import { Prism } from '@mantine/prism';

import { ChevronDown, Report, Vocabulary, Code as CodeIcon, Edit } from 'tabler-icons-react';

import { FileIcon, defaultStyles } from 'react-file-icon';

import useAxiosFetch from '../../hooks/useAxiosFetch';

import { Tooltip, Section, FileTree } from '../../components';


import { JSZip } from 'jszip';
import { useInsertionEffect } from 'react';
// import * as fs from 'fs';

export const TestsResults = () => {
    const { colorScheme } = useMantineColorScheme();

    const { files, setFiles, testsData, setTestsData } = useContext(FilesContext);
    const [openCode, setOpenCode] = useState(false);

    const [files_tree, set_files_tree] = useState('123');

    // console.log(files)
    console.log("TESTDATA:" + JSON.stringify(testsData));
    console.log("TESTDATA TYPE:" + typeof(testsData));



    async function build_tree() {

        // console.log('121212')
        console.log(files[0])
        // let data = fs.readFileSync(file_path)
        let zip = await JSZip.loadAsync(files[0])
        let files_names = Object.keys(zip.files);
        // res = get_tree_schema_as_list(files_names);
        // console.log(build_tree_from_list(res))
        // tree_as_string = build_tree_from_list(res);
        set_files_tree(files_names);

    }

    function clearResults() {
        console.log("CLEARING RESULTS");
        var bodyFormData = new FormData();
        bodyFormData.append('clearResults', "Clear Results Directory");
        bodyFormData.append('fileName', files[0]);

        axios({
            method: "post",
            url: 'http://localhost:4000/clear-results',
            data: bodyFormData,
            headers: { "Content-Type": "multipart/form-data" },
        })
            .then(function (response) {
                //handle success
                console.log(response);
            })
            .catch(function (response) {
                //handle error
                console.log(response);
            });
    }

    useEffect(() => {
        clearResults();
    }, []);

    return (
        <div className='TestsResults'>
            <div className='overall-summary'>
                <Section>
                    <Text align='center' mb='lg' color='dimmed' size='xl' weight='bold'>
                        Overall Summary:
                    </Text>
                </Section>
                <Section className='overall-summary-content'>
                    {Object.keys(testsData.header).map((title) => (<>
                        <Text>
                            <strong style={{ 'text-transform': 'capitalize' }}>
                                {title}
                            </strong>
                        </Text>
                        <Text component='p' mt='0'>
                            {title === 'summary' ? <>
                                {testsData.compilation.errors !== '' || testsData.compilation.exceptions !== ''
                                    ? <><span className='color-error'>Please note! could not compile your files. Please check for the compilation errors below for more information.</span><br /></>
                                    : <></>
                                }
                                {Object.keys(testsData.header.summary).map((entry) =>
                                    <>{entry}: {testsData.header.summary[entry]} <br /></>
                                )}
                            </> : testsData.header[title]}
                        </Text>
                    </>))}

                    <br />
                    <Button onClick={() => { setOpenCode(true); build_tree(); }}>
                        Edit Your Files Online <Edit />
                    </Button>
                </Section>
            </div>

            {
                testsData.compilation.errors !== '' || testsData.compilation.exceptions !== '' ?
                    <>
                        <Section>
                            <Text align='center' mb='lg' color='dimmed' size='xl' weight='bold'>
                                Compilation Failed:
                            </Text>
                        </Section>
                        <Section className='compilation-failed-content'>
                            <Text>
                                <strong style={{ 'text-transform': 'capitalize' }} className='color-error'>
                                    Errors
                                </strong>
                            </Text>
                            <Text component='p' mt='0'>
                                {testsData.compilation.errors || 'There were no compilation errors.'}
                            </Text>
                            <Text>
                                <strong style={{ 'text-transform': 'capitalize' }} className='color-error'>
                                    Exceptions
                                </strong>
                            </Text>
                            <Text component='p' mt='0'>
                                {testsData.compilation.exceptions || 'There were no exceptions.'}
                            </Text>
                        </Section>
                    </>
                    :
                    <>
                        <Section>
                            <Text align='center' mb='lg' color='dimmed' size='xl' weight='bold'>
                                Tests Results:
                            </Text>
                        </Section>
                        <Section>
                            <Accordion className='results-accordion' icon={<ChevronDown />} iconPosition='right' multiple>
                                {Object.keys(testsData.tests).map((testName) =>
                                    createAccordionItem(testName, testsData.tests[testName])
                                )}
                            </Accordion>
                        </Section>
                    </>
            }

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
                    <Text>{getFileTreeStatic()}</Text>
                    <Editor
                        height='80vh'
                        width='70vw'
                        theme={colorScheme === 'dark' ? 'vs-dark' : 'light'}
                        defaultLanguage='javascript'
                        defaultValue='// some comment'
                    />
                </Group>
            </Modal>
        </div>
    );
};

// build_tree()
// async function build_tree(files){

//     console.log('121212')
//     // let file_path = '/front_end_prod/src/api/36_Hw2_312238629.zip'
//     console.log(files)
//     return "fdsfsf"

//     // let data = fs.readFileSync(file_path)
//     // let zip = await JSZip.loadAsync(data)
//     // let files_names =  Object.keys(zip.files);
//     // res = get_tree_schema_as_list(files_names);
//     // console.log(build_tree_from_list(res))
//     // return build_tree_from_list(res);


// }

function get_tree_schema_as_list(files_names) {
    let result = [];
    let level = { result };
    files_names.forEach(path => {
        full_name = path
        path.split('/').reduce((r, name, i, a) => {
            if (!r[name]) {
                r[name] = { result: [] };
                r.result.push({ full_name, name, children: r[name].result })
            }
            return r[name];
        }, level)
    })

    return result;
}

// let a = NaN;
function build_tree_from_list(tree_list) {

    if (tree_list.length === 0 || tree_list.length === undefined) {
        return '';
    }
    res = ''
    for (path of tree_list) {
        // if the next path is directory
        if (path.full_name.slice(-1) === '/') {
            res += (`<FileTree.Folder name=${path.full_name}>\n`
                +
                build_tree_from_list(path.children.slice(1))
                +
                ` </FileTree.Folder>\n`)
        }
        // if the next path is file
        else {
            res += (`<FileTree.File name=${path.full_name}/>\n`)
        }
    }
    return res;
}


const getFileTreeStatic = () => {

    return (
        <FileTree style={{ width: '20vw' }}>
            <FileTree.Folder name='src'>
                <FileTree.Folder name='components'>
                    <FileTree.File name='modules.js' />
                    <FileTree.File name='FileTree.jsx' />
                </FileTree.Folder>
                <FileTree.File name='index.jsx' />
            </FileTree.Folder>
            <FileTree.File name='index.jsx' />
        </FileTree>)
}


// const createFileTree = () => {

//     // fs.readFile(file_name, function(err, data) {
//     //     if (err) throw err;
//     //     JSZip.loadAsync(data).then(function (zip) {
//     //         files = Object.keys(zip.files);
//     //         return <FileTree.File key={files[0]} name={files[0]} />;
//     //     })

//     // })
//     return getFileTreeStatic()
//     return <FileTree.File key='RunTests.jsx' name='RunTests.jsx' />;
// }


// ---- Helper functions ------------------------

const createAccordionItem = (testName, test) => {
    const { colorScheme } = useMantineColorScheme();

    const [view, setView] = useState('summary');

    const { files, setFiles, setTestsData } = useContext(FilesContext);
    const fileNameNoExt = files[0].name.split('.')[0];
    const current = useAxiosFetch('homework/deploy/' + fileNameNoExt + '/myOutput' + testName + '.txt');
    const excepted = useAxiosFetch('homework/deploy/' + fileNameNoExt + '/output' + testName);
    const output = {
        current: current.data.toString(),
        excepted: excepted.data.toString()
    };

    let failed = test.result.toLowerCase().includes('failed');
    if(!test.result){
        failed = true;
    }
    const result = test.result;
    const warning = Boolean(test.warning.trim());
    const grade = Boolean(test.grade.trim());
    const log = Boolean(test.log.trim());
    const error = Boolean(test.error.trim());

    const getSummaryFromTestData = () => {
        return <>
            The test '{testName}' was {failed ? 'failed' : 'passed successfully'}.
            {log && <p>Log details: <Prism>{test.log}</Prism></p>}
            {warning && <p>Warnings: <Prism>{test.warning}</Prism></p>}
            {error && <p>Errors: <Prism>{test.error}</Prism></p>}
            {grade && <p>Grade for this test: <Code>{test.grade}</Code></p>}
        </>;
    };

    return (
        <Accordion.Item
            key={testName}
            label={
                <>
                    <Group noWrap position='apart'>
                        <Group>
                            <div className={`accordion-item-number${failed ? ' failed' : ''}`}>
                                {testName}
                            </div>
                            <div className='accordion-item-title'>
                                <Text>{result}</Text>
                                <Text size='sm' color='dimmed' weight={400}>
                                    {test.grade}
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
                                name={`view-${testName}`}
                                id={`summary-${testName}`}
                                value='summary'
                                hidden
                                defaultChecked
                            />
                            <label htmlFor={`summary-${testName}`}>
                                <Tooltip tip='View test summary'>
                                    <ActionIcon component='div' variant='hover' size='xl'>
                                        <Report />
                                    </ActionIcon>
                                </Tooltip>
                            </label>
                            <input
                                type='radio'
                                name={`view-${testName}`}
                                id={`output-${testName}`}
                                value='output'
                                hidden
                            />
                            <label htmlFor={`output-${testName}`}>
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
                    <Text>{getSummaryFromTestData()}</Text>
                </>
            ) : view === 'output' ? (
                <>
                    <Title order={4} align='center'>
                        Test Output
                    </Title>
                    <br />
                    <Group>
                        <Text style={{ width: '47%' }} weight={700} color="dimmed">Expected Output:</Text>
                        <Text weight={700} color="dimmed">Your Output:</Text>
                    </Group>
                    <DiffEditor
                        original={output.current}
                        modified={output.excepted}
                        theme={colorScheme === 'dark' ? 'vs-dark' : 'light'}
                        height='50vh'
                        language='text/plain'
                    />
                </>
            ) : (
                <></>
            )}
        </Accordion.Item>
    );
};


// function build_tree_from_list(tree_list){

//     let res = '';

//     if (tree_list.length === 0 || tree_list.length === undefined) {
//         return '';
//     }

//     for (path of tree_list){
//         // if the next path is directory
//         if (path.full_name.slice(-1) === '/'){
//             res += (`<FileTree.Folder name=${path.full_name}>\n`
//                     +
//                     build_tree_from_list(path.children.slice(1))
//                     +
//                     ` </FileTree.Folder>\n`)
//         }
//         // if the next path is file
//         else{
//             res += (`<FileTree.File name=${path.full_name}/>\n`)
//         }
//     }
//     return res;
// }

export default TestsResults;

/* ------------------------------------------------------------------------------------------------ */
/* ------------------------------------------------------------------------------------------------ */



// function build_tree(file_path) {
//     file_path = '/front_end_prod/src/api/36_Hw2_312238629.zip'
//     console.log(file_path)
//     console.log('9999999999999999999999999999999999999999999999999999999999')

//     let files_names = get_files_names(file_path);
//     console.log(files_names)

//     res = get_tree_schema_as_list(files_names);
//     console.log(res)
//     return build_tree_from_list(res);
// }


// function get_files_names (file_path) {
//     // var zip = new AdmZip(file_path);
//     console.log(zip)
//     let zipEntries = zip.getEntries(); // an array of ZipEntry records

//     let files_names = []
//     zipEntries.forEach(function (zipEntry) {
//         files_names.push(zipEntry.entryName)
//     });
//     return files_names;
// }


// function get_tree_schema_as_list (files_names) {
//     let result = [];
//     let level = {result};
//     files_names.forEach(path => {
//         full_name = path
//         path.split('/').reduce((r, name, i, a) => {
//           if(!r[name]) {
//             r[name] = {result: []};
//             r.result.push({full_name, name, children: r[name].result})
//           }
//           return r[name];
//         }, level)
//       })
//     return result;
// }
