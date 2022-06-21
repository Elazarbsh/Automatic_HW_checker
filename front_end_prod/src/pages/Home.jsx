/* ------------------------------------------------------------------------------------------------ */
/* ---- React Page Component - Home (Entry Page) -------------------------------------------------- */

import { Title, Text } from '@mantine/core';

import { Section, FileUploader } from '../components';

export const Home = () => {
    return (
        <div className='Home'>
            <Section className={'headings'}>
                <Title className={'main-title'} order={2}>
                    Tel-Hai College <br /> Computer Science Dept.
                </Title>
                <Title className={'sub-title'} order={3}>
                    Homework System
                </Title>
            </Section>
            <Section className={'upload'}>
                <Text align='center' mb='md'>
                    Upload your homework files to evaluate them right now.
                </Text>
                <FileUploader />
            </Section>
        </div>
    );
};

export default Home;

/* ------------------------------------------------------------------------------------------------ */
/* ------------------------------------------------------------------------------------------------ */
