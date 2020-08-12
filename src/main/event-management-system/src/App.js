import React from 'react';
import {createMuiTheme, ThemeProvider} from "@material-ui/core/styles";
import CssBaseline from "@material-ui/core/CssBaseline";
import CustomAppBar from "./common/CustomAppBar";
import {getIsDark, setIsDark} from "./common/Utils";
import AddEventForm from "./usecases/admin/add-event/AddEventForm";
import {BrowserRouter, BrowserRouter as Router, Route, Switch} from "react-router-dom";
import EventDetail from "./usecases/end-user/event-application/EventDetail";
import EventsGrid from "./usecases/end-user/list-all-events/EventsGrid";
import {useHistory, withRouter} from "react-router";
import {createBrowserHistory} from 'history';
import {render} from "react-dom";

export const customHistory = createBrowserHistory();


function App(factory, deps) {

    const history = useHistory();
    const theme = React.useMemo(
        () =>
            createMuiTheme({
                palette: {
                    type: getIsDark() ? "dark" : "light",
                },
            }),
    );

    let [state, setState] = React.useState({textToSearch: ''});


    const handleModeChange = (isDark) => {
        setIsDark(isDark);
        let newState = state;
        setState(newState);
    }

    const handleSearchChanged = (text) => {
        let newState = state;
        newState.textToSearch = text;
        setState(newState);
    }

    const handleNavigateToHomePage = () => {
        history.push('/');
    }

    return (
        <ThemeProvider theme={theme}>
            <CssBaseline/>
            <CustomAppBar onModeChange={handleModeChange}
                          onSearchChanged={handleSearchChanged}
                          onNavigateHomePage={handleNavigateToHomePage}/>
                <div>
                    <EventsGrid textToSearch={state.textToSearch} try={"Merabaaa"}/>
                </div>
            {/*<EventDetail data={{eventTitle: "Deneme"}}/>*/}
        </ThemeProvider>
    );
}

export default App;
