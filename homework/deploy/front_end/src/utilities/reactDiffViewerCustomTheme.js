/* ------------------------------------------------------------------------------------------------ */
/* ---- Custom React-Diff-Viewer Base Styles ------------------------------------------------------ */

export const CUSTOM_DIFF_STYLE = {
    variables: {
        light: {
            diffViewerBackground: 'var(--white)',
            diffViewerColor: 'var(--black)',
            addedBackground: 'var(--red-0)',
            addedColor: 'var(--black)',
            removedBackground: 'var(--green-0)',
            removedColor: 'var(--black)',
            wordAddedBackground: 'var(--red-1)',
            wordRemovedBackground: 'var(--green-2)',
            addedGutterBackground: 'var(--red-1)',
            removedGutterBackground: 'var(--green-2)',
            gutterBackground: 'var(--gray-0)',
            gutterBackgroundDark: 'var(--gray-1)',
            highlightBackground: 'var(--yellow-0)',
            highlightGutterBackground: 'var(--yellow-1)',
            codeFoldGutterBackground: 'var(--gray-2)',
            codeFoldBackground: 'var(--gray-1)',
            emptyLineBackground: 'var(--white)',
            gutterColor: 'var(--black)',
            addedGutterColor: 'var(--black)',
            removedGutterColor: 'var(--black)',
            codeFoldContentColor: 'var(--black)',
            diffViewerTitleBackground: 'var(--gray-0)',
            diffViewerTitleColor: 'var(--black)',
            diffViewerTitleBorderColor: 'var(--gray-1)',
        },
        dark: {
            diffViewerBackground: 'var(--dark-7)',
            diffViewerColor: 'var(--dark-0)',
            addedBackground: '#36191c',
            addedColor: 'var(--white)',
            removedBackground: '#194235',
            removedColor: 'var(--white)',
            wordAddedBackground: '#54262b',
            wordRemovedBackground: '#1b604a',
            addedGutterBackground: '#54262b',
            removedGutterBackground: '#1b604a',
            gutterBackground: 'var(--dark-8)',
            gutterBackgroundDark: 'var(--dark-9)',
            highlightBackground: '#2a3967',
            highlightGutterBackground: '#2d4077',
            codeFoldGutterBackground: 'var(--dark-7)',
            codeFoldBackground: 'var(--dark-6)',
            emptyLineBackground: 'var(--dark-7)',
            gutterColor: 'var(--dark-2)',
            addedGutterColor: 'var(--dark-1)',
            removedGutterColor: 'var(--dark-1)',
            codeFoldContentColor: 'var(--dark-3)',
            diffViewerTitleBackground: 'var(--dark-8)',
            diffViewerTitleColor: 'var(--dark-0)',
            diffViewerTitleBorderColor: 'var(--dark-4)',
        },
    },
    diffContainer: {
        marginBlock: 'var(--size-md)',
        overflow: 'hidden',
        fontSize: '0.8em',
    },
    titleBlock: {
        fontSize: '1.2em',
        fontWeight: 'bold',
        borderBottom: '0',
    },
    gutter: {
        minWidth: '2.4rem',
        padding: '0 0.5rem',
    },
    // diffRemoved?: {}, // style object
    // diffAdded?: {}, // style object
    // marker?: {}, // style object
    // emptyGutter?: {}, // style object
    // highlightedLine?: {}, // style object
    // lineNumber?: {}, // style object
    // highlightedGutter?: {}, // style object
    // contentText?: {}, // style object
    // line?: {}, // style object
    // wordDiff?: {}, // style object
    // wordAdded?: {}, // style object
    // wordRemoved?: {}, // style object
    // codeFoldGutter?: {}, // style object
    // codeFold?: {}, // style object
    // emptyLine?: {}, // style object
    // content?: {}, // style object
    // splitView?: {}, // style object
};

/* ------------------------------------------------------------------------------------------------ */
/* ------------------------------------------------------------------------------------------------ */
