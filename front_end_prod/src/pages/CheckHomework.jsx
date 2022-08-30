/* ------------------------------------------------------------------------------------------------ */
/* ---- React Page Component - Check Homework ----------------------------------------------------- */

import { useState } from 'react';

import { Title, Paper } from '@mantine/core';

import { RunTests, TestsResults } from './';

import { Section } from '../components';

export const CheckHomework = () => {
    const [step, setStep] = useState(1);
    const nextStep = () => setStep((current) => (current < 2 ? current + 1 : current));
    const prevStep = () => setStep((current) => (current > 1 ? current - 1 : current));

    return (
        <div className='CheckHomework'>
            <Section className={'headings'}>
                <Title className={'main-title'} order={2}>
                    Homework Check
                </Title>
                <Title className={'sub-title'} order={3}>
                    { step == 1 ? 'Run Tests' : 'Tests Results' }
                </Title>
            </Section>

            <Section>
                <Paper className='Main-Paper' radius='xl' shadow='lg' p='xl'>
                    { step == 1 ? <RunTests nextStep={nextStep} /> : <TestsResults /> }
                </Paper>
            </Section>
        </div>
    );
};

export default CheckHomework;

/* ------------------------------------------------------------------------------------------------ */
/* ------------------------------------------------------------------------------------------------ */
