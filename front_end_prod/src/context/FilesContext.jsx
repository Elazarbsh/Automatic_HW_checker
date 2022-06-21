/* ------------------------------------------------------------------------------------------------ */
/* ---- React Custom Context - Files State -------------------------------------------------------- */

import { createContext, useState, useEffect } from 'react';
import useAxiosFetch from '../hooks/useAxiosFetch';

const FilesContext = createContext({});

export const FilesProvider = ({ children }) => {
    const [files, setFiles] = useState([]);
    const [testsData, setTestsData] = useState(null);

    return (
        <FilesContext.Provider
            value={{
                files,
                setFiles,
                testsData,
                setTestsData,
            }}
        >
            {children}
        </FilesContext.Provider>
    );
};

export default FilesContext;

/* ------------------------------------------------------------------------------------------------ */
/* ------------------------------------------------------------------------------------------------ */
