import React from 'react';
import {fade, makeStyles, ThemeProvider} from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import IconButton from '@material-ui/core/IconButton';
import Typography from '@material-ui/core/Typography';
import SearchIcon from '@material-ui/icons/Search';
import FormControlLabel from "@material-ui/core/FormControlLabel";
import Switch from "@material-ui/core/Switch";
import FormGroup from "@material-ui/core/FormGroup";
import InputBase from "@material-ui/core/InputBase";
import {isDark} from "./Utils";
import MenuItem from "@material-ui/core/MenuItem";
import TextField from "@material-ui/core/TextField";
import Select from "@material-ui/core/Select";



const useStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1,
    },
    input: {
        color: fade(theme.palette.common.white, 0.85),
        padding: "8px"
    },
    inputBackground: {
        borderRadius: theme.shape.borderRadius,
        backgroundColor: fade(theme.palette.common.white, 0.15),
    },
    menuButton: {
        marginRight: theme.spacing(2),
    },
    title: {
        display: "inherit",
        [theme.breakpoints.up('sm')]: {
            display: 'block',
        },
    },
    search: {
        position: 'relative',
        borderRadius: theme.shape.borderRadius,
        backgroundColor: fade(theme.palette.common.white, 0.15),
        '&:hover': {
            backgroundColor: fade(theme.palette.common.white, 0.25),
        },
        marginRight: theme.spacing(2),
        marginLeft: 0,
        width: '100%',
        [theme.breakpoints.up('sm')]: {
            marginLeft: theme.spacing(3),
            width: 'auto',
        },
    },
    dropdown:{
        position: 'relative',
        marginRight: theme.spacing(2),
        marginLeft: 0,
        width: '30ch',
        verticalAlign: 'center',
        alignItems: 'center',
        justifyContent: 'center',
        background: "inherit"
    },
    searchIcon: {
        padding: theme.spacing(0, 2),
        height: '100%',
        position: 'absolute',
        pointerEvents: 'none',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
    },
    inputRoot: {
        color: 'inherit',
    },
    inputInput: {
        padding: theme.spacing(1, 1, 1, 0),
        // vertical padding + font size from searchIcon
        paddingLeft: `calc(1em + ${theme.spacing(4)}px)`,
        transition: theme.transitions.create('width'),
        width: '100%',
        [theme.breakpoints.up('md')]: {
            width: '100ch',
        },
    },
    switch: {
        position: 'relative',
        marginRight: theme.spacing(2),
        marginLeft: 0,
        width: '20ch',
        verticalAlign: 'center',
        alignItems: 'center',
        justifyContent: 'center'
    },
}));


export default function CustomAppBar() {
    const classes = useStyles();

    return (
        <div className={classes.root} >
            <AppBar position="static" color={isDark ? "default" : "primary"}>
                <Toolbar>
                    <Typography variant="h6" className={classes.title}>
                        Etkinlik Yönetimi
                    </Typography>
                    <div className={classes.search}>
                        <div className={classes.searchIcon}>
                            <SearchIcon />
                        </div>
                        <InputBase
                            placeholder="Etkinlik ara…"
                            classes={{
                                root: classes.inputRoot,
                                input: classes.inputInput,
                            }}
                            inputProps={{ 'aria-label': 'search' }}
                        />

                    </div>
                    <div className={classes.dropdown}>
                    <Select
                        name="type"
                        hiddenLabel
                        defaultValue={0}
                        fullWidth
                        classes={{
                            root: classes.input,
                            icon: classes.input
                        }}
                        className={classes.inputBackground}
                        disableUnderline
                    >
                        <MenuItem value={0} disabled>Etkinlik Zamanı</MenuItem>
                        <MenuItem value={1}>Bir hafta içinde</MenuItem>
                        <MenuItem value={2}>Bir ay içinde</MenuItem>
                        <MenuItem value={3}>Daha ileri tarihte</MenuItem>
                    </Select>
                    </div>
                    <div className={classes.dropdown}>
                        <Select
                            name="type"
                            hiddenLabel
                            defaultValue={0}
                            fullWidth
                            classes={{
                                root: classes.input,
                                icon: classes.input
                            }}
                            className={classes.inputBackground}
                            disableUnderline
                        >
                            <MenuItem value={0}>Tüm etkinlikler</MenuItem>
                            <MenuItem value={1}>Sadece yakınımdakiler</MenuItem>
                        </Select>
                    </div>
                    <FormGroup className={classes.switch}>
                        <FormControlLabel
                            control={<Switch aria-label="login switch"/>}
                            label="Gece Modu"
                        />
                    </FormGroup>
                </Toolbar>
            </AppBar>
        </div>
    );
}
