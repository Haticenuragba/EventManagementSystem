import React from 'react';
import {fade, makeStyles} from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import SearchIcon from '@material-ui/icons/Search';
import FormControlLabel from "@material-ui/core/FormControlLabel";
import Switch from "@material-ui/core/Switch";
import FormGroup from "@material-ui/core/FormGroup";
import InputBase from "@material-ui/core/InputBase";
import {getIsDark, ROLE, TOKEN, USERNAME} from "./Utils";
import MenuItem from "@material-ui/core/MenuItem";
import Select from "@material-ui/core/Select";
import HomeIcon from '@material-ui/icons/Home';
import {useHistory} from "react-router";
import Button from "@material-ui/core/Button";
import {stopNotificationService} from "../usecases/push-notification/PushNotification";



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
            width: "2vw"
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
        width: '15vw',
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
            width: '35vw',
        },
    },
    switch: {
        position: 'absolute',
        marginRight: theme.spacing(2),
        marginLeft: 0,
        verticalAlign: 'center',
        alignItems: 'center',
        justifyContent: 'center',
        right: 0
    },
}));



export default function CustomAppBar(props) {
    const classes = useStyles();
    const history = useHistory();
    const [state, setState] = React.useState({
        visibilityOfParams: window.location.pathname === '/events'
        || window.location.pathname === '/manager/events'
        || window.location.pathname === '/admin/events' ?
            "visible" : "hidden",
        visibilityOfLogout: window.location.pathname === '/manager/events'
        || window.location.pathname === '/admin/events' ?
            "visible" : "hidden",
    });


    const handleModeChange = (event) => {
        props.onModeChange(event.target.checked);
    };


    const navigateToEventsGrid = () => {
        props.onNavigateHomePage();
    }

    const logout = () => {
        localStorage.removeItem(TOKEN);
        localStorage.removeItem(ROLE);
        localStorage.removeItem(USERNAME);
        history.push("/");
        stopNotificationService();
    }


    history.listen((location, action) => {
        let visibility = location.pathname === '/events' || location.pathname === '/manager/events' || location.pathname === '/admin/events' ? "visible" : "hidden"
        let visibilityOfLogout = location.pathname === '/manager/events' || location.pathname === '/admin/events' ? "visible" : "hidden"
        setState({
            visibilityOfParams: visibility,
            visibilityOfLogout: visibilityOfLogout
        });
    })

    const handleSearchChange = (e) => {
        let value = e.target.value;
        props.onSearchChange(value);
    }

    const handleDistanceChange = (e) => {
        let value = e.target.value;
        props.onDistanceChange(value);
    };

    const handleDateSelectionChange = (e) => {
        let value = e.target.value;
        props.onDateSelectionChange(value);
    };

    return (
        <div className={classes.root} >
            <AppBar position="static" color={getIsDark() ? "default" : "primary"}>
                <Toolbar>
                    <HomeIcon className={classes.title} onClick={() => navigateToEventsGrid()}/>
                    <div className={classes.search} style={{visibility: state.visibilityOfParams}}>
                        <div className={classes.searchIcon}>
                            <SearchIcon />
                        </div>
                        <InputBase
                            onChange={handleSearchChange}
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
                        style={{visibility: state.visibilityOfParams}}
                        onChange={handleDateSelectionChange}
                    >
                        <MenuItem value={0} >Tüm tarihler</MenuItem>
                        <MenuItem value={1}>Bir hafta içinde</MenuItem>
                        <MenuItem value={2}>Bir ay içinde</MenuItem>
                    </Select>
                    </div>
                    <div className={classes.dropdown}>
                        <Select
                            name="type"
                            hiddenLabel
                            defaultValue={15000}
                            fullWidth
                            classes={{
                                root: classes.input,
                                icon: classes.input
                            }}
                            className={classes.inputBackground}
                            disableUnderline
                            style={{visibility: state.visibilityOfParams}}
                            onChange={handleDistanceChange}

                        >
                            <MenuItem value={15000}>Tüm etkinlikler</MenuItem>
                            <MenuItem value={100}>Sadece yakınımdakiler</MenuItem>
                        </Select>
                    </div>
                    <div className={classes.dropdown}>
                     <Button onClick={logout} style={{visibility: state.visibilityOfLogout}} color={"secondary"} variant={"contained"}>Çıkış Yap</Button>
                    </div>
                    <FormGroup className={classes.switch}>
                        <FormControlLabel
                            checked={getIsDark()}
                            control={<Switch aria-label="login switch"/>}
                            label="Gece Modu"
                            onChange={handleModeChange}
                        />
                    </FormGroup>
                </Toolbar>
            </AppBar>
        </div>
    );
}

