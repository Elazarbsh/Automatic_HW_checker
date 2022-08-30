/* ------------------------------------------------------------------------------------------------ */
/* ---- React App Component ----------------------------------------------------------------------- */

import { useEffect } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

import { FilesProvider } from './context/FilesContext';

import { MantineProvider, ColorSchemeProvider } from '@mantine/core';
import { NotificationsProvider } from '@mantine/notifications';
import { useHotkeys, useLocalStorage } from '@mantine/hooks';

import { EMOTION_OPTIONS, CUSTOM_THEME_BASE, CUSTOM_THEME_DEFAULTS, CUSTOM_THEME_STYLES } from './utils';

import { PageTemplate } from './components';
import { Home, CheckHomework } from './pages';

const App = () => {

    // Binding the `colorScheme` to the localStorage (remember user preferred colorScheme).
    const [colorScheme, setColorScheme] = useLocalStorage({
        key: 'telhai-color-scheme',
        defaultValue: 'light',
        getInitialValueInEffect: true,
    });

    // Update colorScheme throughout the app (if no value was given toggle between `dark` & `light`).
    const toggleColorScheme = (newColorScheme) => {
        if (newColorScheme) setColorScheme(newColorScheme);
        else setColorScheme(colorScheme === 'dark' ? 'light' : 'dark');
        
        document.documentElement.setAttribute('data-theme', colorScheme);
        const bgColor = getComputedStyle(document.documentElement).getPropertyValue('--body-bg');
        document.querySelectorAll('.app-bar-theme-color').forEach(element => element.setAttribute('content', bgColor))
    };

    // Toggle colorScheme via 'Ctrl+J' on windows or 'Cmd+J' on mac.
    useHotkeys([['mod+J', () => toggleColorScheme()]]);

    // Applying the colorScheme from the local storage on App mounting (first render).
    useEffect(() => {
        document.documentElement.setAttribute('data-theme', colorScheme);
        const bgColor = getComputedStyle(document.documentElement).getPropertyValue('--body-bg');
        document.querySelectorAll('.app-bar-theme-color').forEach(element => element.setAttribute('content', bgColor))
    }, [colorScheme]);



    return (
        <FilesProvider>
            <ColorSchemeProvider colorScheme={colorScheme} toggleColorScheme={toggleColorScheme}>
                <MantineProvider
                    emotionOptions={EMOTION_OPTIONS}
                    theme={{
                        ...CUSTOM_THEME_BASE,
                        colorScheme,
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
