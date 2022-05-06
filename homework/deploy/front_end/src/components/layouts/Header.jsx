/* ------------------------------------------------------------------------------------------------ */
/* ---- React Component - App Header -------------------------------------------------------------- */

import { useContext } from 'react';
import FilesContext from '../../context/FilesContext';
import { useLocation, useNavigate } from 'react-router-dom';

import { ActionIcon, Container, Group } from '@mantine/core';

import { ArrowLeft } from 'tabler-icons-react';

import { Tooltip, Logo, ThemeSwitch } from '../../components';

export const Header = ({ size = 'md' }) => {
    const location = useLocation();
    const navigate = useNavigate();
    const { setFiles } = useContext(FilesContext);

    const goBack = () => {
        setFiles([]);
        navigate('/');
    };

    return (
        <>
            <header className={`Header`}>
                <Container component={Group} className={`Header-Container`} position='apart' size={size}>
                    {location.pathname === '/check-homework' ? (
                        // <Tooltip tip='Go back to the home Page' position='auto'>
                        <Logo to='/' onClick={goBack} />
                    ) : (
                        // </Tooltip>
                        <Logo to='/' />
                    )}
                    <ThemeSwitch />
                </Container>
            </header>
        </>
    );
};

export default Header;

/* ------------------------------------------------------------------------------------------------ */
/* ------------------------------------------------------------------------------------------------ */
