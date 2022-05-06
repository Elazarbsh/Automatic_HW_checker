/* ------------------------------------------------------------------------------------------------ */
/* ---- React Component - ThemeSwitch ------------------------------------------------------------- */

import { useMantineColorScheme, Switch } from '@mantine/core';
import { Sun, MoonStars } from 'tabler-icons-react';

import { Tooltip } from '../../components';

export const ThemeSwitch = () => {
    const { colorScheme, toggleColorScheme } = useMantineColorScheme();

    return (
        <Tooltip tip={`Switch to ${colorScheme === 'dark' ? 'light' : 'dark'} theme`} position='auto'>
            <div className={'ThemeSwitch'}>
                <Sun className={'icon icon-sun'} size='1.4rem' />
                <MoonStars className={'icon icon-moon'} size='1.4rem' />
                <Switch
                    className={'switch'}
                    checked={colorScheme === 'dark'}
                    onChange={() => toggleColorScheme()}
                />
            </div>
        </Tooltip>
    );
};

export default ThemeSwitch;

/* ------------------------------------------------------------------------------------------------ */
/* ------------------------------------------------------------------------------------------------ */
