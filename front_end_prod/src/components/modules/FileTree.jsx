import { useState } from 'react';
import { Link } from 'react-router-dom';

import { Container, Group, Title, Box, Text, useMantineTheme } from '@mantine/core';
import { Dropzone, IMAGE_MIME_TYPE } from '@mantine/dropzone';

import { Upload, Photo, X, Icons as TablerIcon, FileText } from 'tabler-icons-react';

import {} from '../index';

// ------------------------------------
import {
    BrandJavascript,
    BrandCss3,
    BrandHtml5,
    BrandReactNative,
    Folder as IconFolder,
    FileCode,
} from 'tabler-icons-react';

const FILE_ICONS = {
    js: <BrandJavascript />,
    css: <BrandCss3 />,
    html: <BrandHtml5 />,
    jsx: <BrandReactNative />,
};

// ------------------------------------

const File = ({ name, style }) => {
    let ext = name.split('.')[1];

    return (
        <Box
            sx={(theme) => ({
                paddingLeft: '20px',
                display: 'flex',
                alignItems: 'center',
                ...style,
            })}
        >
            {FILE_ICONS[ext] || <FileCode />}
            <span
                style={{
                    marginLeft: '5px',
                }}
            >
                {name}
            </span>
        </Box>
    );
};

// ------------------------------------

const Folder = ({ name, children, open = false }) => {
    const [isOpen, setIsOpen] = useState(!open);

    const handleToggle = (e) => {
        e.preventDefault();
        setIsOpen(!isOpen);
    };

    return (
        <Box
            sx={{
                paddingLeft: '20px',
            }}
        >
            <Box
                onClick={handleToggle}
                sx={{
                    display: 'flex',
                    alignItems: 'center',
                    cursor: 'pointer',
                    '&:hover': {
                        textDecoration: 'underline',
                    },
                }}
            >
                <IconFolder />
                <span style={{ marginLeft: '5px' }}>{name}</span>
            </Box>
            <Box
                className={isOpen || 'open'}
                sx={{
                    overflow: 'hidden',
                    height: '0',
                    '&.open': {
                        height: 'auto',
                    },
                }}
            >
                {children}
            </Box>
        </Box>
    );
};

// ------------------------------------

export const FileTree = ({ children }) => {
    return <Box className='FileTree'>{children}</Box>;
};

// ------------------------------------

FileTree.File = File;
FileTree.Folder = Folder;

export default FileTree;
