import { Tooltip as Base } from '@mantine/core';

export function Tooltip({ children, tip = 'add a tip property', ...props }) {
    return (
        <Base
            label={tip}
            gutter={8}
            withArrow
            arrowSize={2.5}
            transition='none'
            transitionDuration={80}
            transitionTimingFunction='cubic-bezier(0.65, 0.05, 0.36, 1)'
            openDelay={400}
            {...props}
        >
            {children}
        </Base>
    );
}

export default Tooltip;
