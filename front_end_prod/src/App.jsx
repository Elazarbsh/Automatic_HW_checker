/* ------------------------------------------------------------------------------------------------ */
/* ---- React App Component ----------------------------------------------------------------------- */

import { useEffect, useState } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

import { FilesProvider } from './context/FilesContext';

import { MantineProvider, ColorSchemeProvider } from '@mantine/core';
import { NotificationsProvider } from '@mantine/notifications';
import { useHotkeys, useLocalStorage } from '@mantine/hooks';

import { EMOTION_OPTIONS, CUSTOM_THEME_BASE, CUSTOM_THEME_DEFAULTS, CUSTOM_THEME_STYLES } from './utilities';

import { PageTemplate } from './components';
import { Home, CheckHomework } from './pages';

const App = () => {
    const [colorScheme, setColorScheme] = useLocalStorage({
        key: 'telhai-color-scheme',
        defaultValue: 'light',
        getInitialValueInEffect: true,
    });

    const toggleColorScheme = (value) => {
        document.documentElement.setAttribute('data-theme', colorScheme);
        setColorScheme(value || (colorScheme === 'dark' ? 'light' : 'dark'));
    };

    useHotkeys([['mod+J', () => toggleColorScheme()]]);

    useEffect(() => {
        document.documentElement.setAttribute('data-theme', colorScheme);
    }, [colorScheme]);

    return (
        <FilesProvider>
            <ColorSchemeProvider colorScheme={colorScheme} toggleColorScheme={toggleColorScheme}>
                <MantineProvider
                    emotionOptions={EMOTION_OPTIONS}
                    theme={{
                        ...CUSTOM_THEME_BASE,
                        ...{
                            primaryColor: 'primary',
                            colorScheme,
                        },
                    }}
                    styles={CUSTOM_THEME_STYLES}
                    defaultProps={CUSTOM_THEME_DEFAULTS}
                >
                    <NotificationsProvider position='top-right' autoClose={3000}>
                        <BrowserRouter>
                            <Routes>
                                <Route path='/' element={<PageTemplate />}>
                                    <Route index element={<Home />} />
                                    <Route path='check-homework' element={<CheckHomework />} />
                                    <Route path='*' element={<Home />} />
                                </Route>
                            </Routes>
                        </BrowserRouter>
                    </NotificationsProvider>
                </MantineProvider>
            </ColorSchemeProvider>
        </FilesProvider>
    );
};

export default App;

/* ------------------------------------------------------------------------------------------------ */
/* ------------------------------------------------------------------------------------------------ */
