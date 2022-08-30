/* ------------------------------------------------------------------------------------------------ */
/* ---- Custom Mantine Base Styles ---------------------------------------------------------------- */

//
export const EMOTION_OPTIONS = {
    key: 'e',
};

// Mantine MantineProvider theme object.
export const CUSTOM_THEME_BASE = {
    // Base configuration
    dir: 'ltr', // 'ltr' | 'rtl'.
    datesLocale: 'en', // Default dates formatting locale used in every @mantine/dates component.
    dateFormat: 'MMMM D, YYYY', // Default date format used in DatePicker and DateRangePicker components.
    focusRing: 'auto', // display focus ring only when user navigates with keyboard.
    loader: 'oval', // 'oval' | 'bars' | 'dots'. Default loader used in Loader and LoadingOverlay components.

    // Sizings
    spacing: {
        '2xs': 'var(--size-2xs) ',
        xs: 'var(--size-xs) ',
        sm: 'var(--size-sm) ',
        md: 'var(--size-md) ',
        lg: 'var(--size-lg) ',
        xl: 'var(--size-xl) ',
        '2xl': 'var(--size-2xl) ',
        '3xl': 'var(--size-3xl) ',
        fluid: {
            sm: 'var(--size-fluid-sm)',
            md: 'var(--size-fluid-md)',
            lg: 'var(--size-fluid-lg)',
            xl: 'var(--size-fluid-xl)',
            '2xl': 'var(--size-fluid-2xl)',
        },
    },

    // Breakpoints
    breakpoints: {
        xs: 360,
        sm: 576,
        md: 768,
        lg: 1024,
        xl: 1440,
        '2xl': 1920,
    },

    // Typography
    fontFamily: 'var(--ff-body)', // Main font-family used in most components.
    fontFamilyMonospace: 'var(--ff-code)', // Monospace font-family, used in Code, Kbd and Prism components.
    lineHeight: 1.5, // Base line-height used in most components.
    headings: {
        fontFamily: 'var(--ff-title)',
        fontWeight: 'var(--fw-black)',
        sizes: {
            h1: { fontSize: 'var(--fs-3xl)', lineHeight: 1.5 },
            h2: { fontSize: 'var(--fs-2xl)', lineHeight: 1.5 },
            h3: { fontSize: 'var(--fs-xl)', lineHeight: 1.4 },
            h4: { fontSize: 'var(--fs-lg)', lineHeight: 1.4 },
            h5: { fontSize: 'var(--fs-md)', lineHeight: 1.3 },
            h6: { fontSize: 'var(--fs-sm)', lineHeight: 1.3 },
        },
    },
    fontSizes: {
        xs: 'var(--fs-xs)',
        sm: 'var(--fs-sm)',
        md: 'var(--fs-md)',
        lg: 'var(--fs-lg)',
        xl: 'var(--fs-xl)',
        '2xl': 'var(--fs-2xl)',
        '3xl': 'var(--fs-3xl)',
        '4xl': 'var(--fs-4xl)',
        fluid: {
            sm: 'var(--fs-fluid-sm)',
            md: 'var(--fs-fluid-md)',
            lg: 'var(--fs-fluid-lg)',
            xl: 'var(--fs-fluid-xl)',
            '2xl': 'var(--fs-fluid-2xl)',
        },
    },

    // Border Radius
    radius: {
        xs: 'var(--radius-xs)',
        sm: 'var(--radius-sm)',
        md: 'var(--radius-md)',
        lg: 'var(--radius-lg)',
        xl: 'var(--radius-xl)',
        ellipse: 'var(--radius-ellipse)',
        round: 'var(--radius-round)',
    },
    defaultRadius: 'md', // must be a key of theme.radius, or a number.

    // Animation
    transitionTimingFunction: 'var(--a-fn-default)', // Timing function used for animations.

    // Colors
    colorScheme: 'light', // 'dark' | 'light'.
    white: 'var(--white)', // style base white.
    black: 'var(--black)', // style base black.
    // Note: each color must be an array of 10 colors (shades) as string.
    colors: {
        primary: [
            'var(--primary-0)',
            'var(--primary-1)',
            'var(--primary-2)',
            'var(--primary-3)',
            'var(--primary-4)',
            'var(--primary-5)',
            'var(--primary-6)',
            'var(--primary-7)',
            'var(--primary-8)',
            'var(--primary-9)',
        ],
        accent: [
            'var(--accent-0)',
            'var(--accent-1)',
            'var(--accent-2)',
            'var(--accent-3)',
            'var(--accent-4)',
            'var(--accent-5)',
            'var(--accent-6)',
            'var(--accent-7)',
            'var(--accent-8)',
            'var(--accent-9)',
        ],
        secondary: [
            'var(--secondary-0)',
            'var(--secondary-1)',
            'var(--secondary-2)',
            'var(--secondary-3)',
            'var(--secondary-4)',
            'var(--secondary-5)',
            'var(--secondary-6)',
            'var(--secondary-7)',
            'var(--secondary-8)',
            'var(--secondary-9)',
        ],
        dark: [
            'var(--dark-0)',
            'var(--dark-1)',
            'var(--dark-2)',
            'var(--dark-3)',
            'var(--dark-4)',
            'var(--dark-5)',
            'var(--dark-6)',
            'var(--dark-7)',
            'var(--dark-8)',
            'var(--dark-9)',
        ],
        gray: [
            'var(--gray-0)',
            'var(--gray-1)',
            'var(--gray-2)',
            'var(--gray-3)',
            'var(--gray-4)',
            'var(--gray-5)',
            'var(--gray-6)',
            'var(--gray-7)',
            'var(--gray-8)',
            'var(--gray-9)',
        ],
        red: [
            'var(--red-0)',
            'var(--red-1)',
            'var(--red-2)',
            'var(--red-3)',
            'var(--red-4)',
            'var(--red-5)',
            'var(--red-6)',
            'var(--red-7)',
            'var(--red-8)',
            'var(--red-9)',
        ],
        pink: [
            'var(--pink-0)',
            'var(--pink-1)',
            'var(--pink-2)',
            'var(--pink-3)',
            'var(--pink-4)',
            'var(--pink-5)',
            'var(--pink-6)',
            'var(--pink-7)',
            'var(--pink-8)',
            'var(--pink-9)',
        ],
        grape: [
            'var(--grape-0)',
            'var(--grape-1)',
            'var(--grape-2)',
            'var(--grape-3)',
            'var(--grape-4)',
            'var(--grape-5)',
            'var(--grape-6)',
            'var(--grape-7)',
            'var(--grape-8)',
            'var(--grape-9)',
        ],
        violet: [
            'var(--violet-0)',
            'var(--violet-1)',
            'var(--violet-2)',
            'var(--violet-3)',
            'var(--violet-4)',
            'var(--violet-5)',
            'var(--violet-6)',
            'var(--violet-7)',
            'var(--violet-8)',
            'var(--violet-9)',
        ],
        indigo: [
            'var(--indigo-0)',
            'var(--indigo-1)',
            'var(--indigo-2)',
            'var(--indigo-3)',
            'var(--indigo-4)',
            'var(--indigo-5)',
            'var(--indigo-6)',
            'var(--indigo-7)',
            'var(--indigo-8)',
            'var(--indigo-9)',
        ],
        blue: [
            'var(--blue-0)',
            'var(--blue-1)',
            'var(--blue-2)',
            'var(--blue-3)',
            'var(--blue-4)',
            'var(--blue-5)',
            'var(--blue-6)',
            'var(--blue-7)',
            'var(--blue-8)',
            'var(--blue-9)',
        ],
        ocean: [
            'var(--ocean-0)',
            'var(--ocean-1)',
            'var(--ocean-2)',
            'var(--ocean-3)',
            'var(--ocean-4)',
            'var(--ocean-5)',
            'var(--ocean-6)',
            'var(--ocean-7)',
            'var(--ocean-8)',
            'var(--ocean-9)',
        ],
        cyan: [
            'var(--cyan-0)',
            'var(--cyan-1)',
            'var(--cyan-2)',
            'var(--cyan-3)',
            'var(--cyan-4)',
            'var(--cyan-5)',
            'var(--cyan-6)',
            'var(--cyan-7)',
            'var(--cyan-8)',
            'var(--cyan-9)',
        ],
        teal: [
            'var(--teal-0)',
            'var(--teal-1)',
            'var(--teal-2)',
            'var(--teal-3)',
            'var(--teal-4)',
            'var(--teal-5)',
            'var(--teal-6)',
            'var(--teal-7)',
            'var(--teal-8)',
            'var(--teal-9)',
        ],
        green: [
            'var(--green-0)',
            'var(--green-1)',
            'var(--green-2)',
            'var(--green-3)',
            'var(--green-4)',
            'var(--green-5)',
            'var(--green-6)',
            'var(--green-7)',
            'var(--green-8)',
            'var(--green-9)',
        ],
        lime: [
            'var(--lime-0)',
            'var(--lime-1)',
            'var(--lime-2)',
            'var(--lime-3)',
            'var(--lime-4)',
            'var(--lime-5)',
            'var(--lime-6)',
            'var(--lime-7)',
            'var(--lime-8)',
            'var(--lime-9)',
        ],
        yellow: [
            'var(--yellow-0)',
            'var(--yellow-1)',
            'var(--yellow-2)',
            'var(--yellow-3)',
            'var(--yellow-4)',
            'var(--yellow-5)',
            'var(--yellow-6)',
            'var(--yellow-7)',
            'var(--yellow-8)',
            'var(--yellow-9)',
        ],
        amber: [
            'var(--amber-0)',
            'var(--amber-1)',
            'var(--amber-2)',
            'var(--amber-3)',
            'var(--amber-4)',
            'var(--amber-5)',
            'var(--amber-6)',
            'var(--amber-7)',
            'var(--amber-8)',
            'var(--amber-9)',
        ],
        orange: [
            'var(--orange-0)',
            'var(--orange-1)',
            'var(--orange-2)',
            'var(--orange-3)',
            'var(--orange-4)',
            'var(--orange-5)',
            'var(--orange-6)',
            'var(--orange-7)',
            'var(--orange-8)',
            'var(--orange-9)',
        ],
    },
    primaryColor: 'primary', // must be a key of theme.colors, cannot be actual color value.
    primaryShade: {
        light: 6,
        dark: 8,
    },

    // Shadows
    shadows: {
        xs: 'var(--shadow-xs)',
        sm: 'var(--shadow-sm)',
        md: 'var(--shadow-md)',
        lg: 'var(--shadow-lg)',
        xl: 'var(--shadow-xl)',
    },

    // Optional custom styles
    other: {
        fontWeights: {
            thin: 100,
            light: 'var(--fw-light)',
            normal: 'var(--fw-normal)',
            bold: 'var(--fw-bold)',
            black: 'var(--fw-black)',
        },
        glows: {
            xs: 'var(--glow-1)',
            sm: 'var(--glow-2)',
            md: 'var(--glow-3)',
            lg: 'var(--glow-4)',
            xl: 'var(--glow-5)',
        },
        colors: {
            danger: 'var(--danger)',
            success: 'var(--success)',
            warning: 'var(--warning)',
            info: 'var(--info)',
        },
    },
};

// Add custom styles to the theme
export const CUSTOM_THEME_DEFAULTS = {
    Avatar: {
        placeholder: {
            backgroundColor: 'var(--gray-1)',
            color: 'red',
        },
    },

    // Button: { color: 'blue' },
    // Badge: { size: 'xl', radius: 0 },
};

// Mantine components style fixes.
export const CUSTOM_THEME_STYLES = {
    Avatar: (theme, params) => ({
        placeholder: {
            backgroundColor: theme.colorScheme === 'light' ? theme.colors.gray[1] : theme.colors.dark[5],
        },
    }),

    Button: (theme, params) => ({
        // Shared button styles are applied to all buttons
        root: {
            height: 42,
            padding: '0 30px',
        },

        filled: {
            // subscribe to component params
            // color: theme.colors[params.color || theme.primaryColor][1],
        },

        // These styles are applied only to buttons with outline variant
        outline: {
            // You can use any selectors inside (the same way as in createStyles function)
            '&:hover': {
                backgroundColor: theme.colorScheme === 'dark' ? theme.colors.dark[8] : theme.colors.gray[0],
            },
        },
    }),

    Anchor: (theme, params) => ({
        root: {
            fontSize: 'inherit',
            borderRadius: theme.radius[theme.defaultRadius],
        },
    }),

    Switch: (theme, params) => ({
        input: {
            '&:checked::after': {
                transform: 'translateX(-120%)',
            },
        },
    }),

    TextInput: (theme, params) => ({
        error: {
            fontSize: theme.fontSizes.sm,
        },
    }),

    PasswordInput: (theme, params) => ({
        description: {
            fontSize: theme.fontSizes.xs,
        },
        error: {
            fontSize: theme.fontSizes.sm,
        },
    }),

    Notification: (theme, params) => ({
        root: {
            '&::before': {
                top: theme.spacing['2xs'],
                bottom: theme.spacing['2xs'],
            },
        },
    }),

    Tooltip: (theme, params) => ({
        body: {
            padding: theme.spacing.xs,
        },
    }),

    Menu: (theme, params) => ({
        body: {
            padding: theme.spacing.md,
        },
        item: {
            padding: `${theme.spacing.xs} ${theme.spacing.xs}`,

            '&:focus': {
                outlineOffset: '2px',
                outline: `2px solid ${
                    theme.colors[theme.primaryColor][theme.colorScheme === 'dark' ? 7 : 5]
                }`,
            },
            '&:focus:not(:focus-visible)': {
                outline: 'none',
            },
        },
    }),

    Checkbox: (theme, params) => ({
        label: {
            paddingLeft: theme.spacing.xs,
        },
    }),

    Code: (theme, params) => ({
        root: {
            padding: `${theme.spacing['2xs']} ${theme.spacing.xs}`,
            fontSize: theme.fontSizes.sm,
            backgroundColor: theme.colorScheme === 'light' ? theme.colors.gray[1] : theme.colors.dark[9]
        },
    }),
    
    Prism: (theme, params) => ({
        root: {
            borderRadius: theme.radius.sm,
            padding: `${theme.spacing['2xs']} ${theme.spacing.xs}`,
            backgroundColor: theme.colorScheme === 'light' ? theme.colors.gray[1] : theme.colors.dark[9]
        },
        
        lineContent: {
            fontSize: theme.fontSizes.sm,
            backgroundColor: theme.colorScheme === 'light' ? theme.colors.gray[1] : theme.colors.dark[9]
        },

        copy: {
            top: `calc(var(--size-2xs) / 2)`
        }
    }),
};

/* ------------------------------------------------------------------------------------------------ */
/* ------------------------------------------------------------------------------------------------ */
