import { LoadingOverlay, Loader as MantineLoader } from '@mantine/core';

export const Loader = ({ fullPage = false, visible, ...props }) => {
    return (
        <>
            {fullPage ? (
                <div className='loader' {...props}>
                    <LoadingOverlay
                        transitionDuration={120}
                        visible={visible}
                        loaderProps={{ height: '6rem', width: '6rem' }}
                        variant='dots'
                    />
                </div>
            ) : (
                <div className='loader' {...props}>
                    <MantineLoader visible={visible} height='6rem' width='6rem' variant='dots' />
                </div>
            )}
        </>
    );
};

export default MantineLoader;
