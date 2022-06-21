/* ------------------------------------------------------------------------------------------------ */
/* ---- React Page Component - Check Homework ----------------------------------------------------- */

import { useState } from 'react';
import { Link } from 'react-router-dom';

import { Title, Paper, Stepper, Button, Group, Text } from '@mantine/core';
import { Prism } from '@mantine/prism';

import { FileCheck, Report, FileCode } from 'tabler-icons-react';

import { RunTests, TestsResults, ViewOutput } from './';

import { Section } from '../components';

export const CheckHomework = () => {
    const [step, setStep] = useState(1);
    const nextStep = () => setStep((current) => (current < 3 ? current + 1 : current));
    const prevStep = () => setStep((current) => (current > 1 ? current - 1 : current));

    return (
        <div className='CheckHomework'>
            <Section className={'headings'}>
                <Title className={'main-title'} order={2}>
                    Homework Check
                </Title>
                <Title className={'sub-title'} order={3}>
                    {step <= 1 ? 'Run Tests' : step === 2 ? 'Tests Results' : 'View Output'}
                </Title>
            </Section>

            <Section>
                <Paper className='Main-Paper' radius='xl' shadow='lg' p='xl'>
                    {step <= 1 ? (
                        <RunTests nextStep={nextStep} />
                    ) : step === 2 ? (
                        <TestsResults />
                    ) : (
                        <ViewOutput />
                    )}
                </Paper>
            </Section>
        </div>
    );
};

export default CheckHomework;

/* ------------------------------------------------------------------------------------------------ */
/* ------------------------------------------------------------------------------------------------ */
