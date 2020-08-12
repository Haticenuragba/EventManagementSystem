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

    const [state, setState] = React.useState({});

    const handleModeChange = (isDark) => {
        setIsDark(isDark);
        setState({});
    }

    const handleNavigateToHomePage = () => {
        history.push('/');
    }



    return (
        <ThemeProvider theme={theme}>
            <CssBaseline/>
            <CustomAppBar onModeChange={handleModeChange} onNavigateHomePage={handleNavigateToHomePage}/>
                <div>
                    <Switch>
                        <Route  path="/events/:eventTitle" component={withRouter(EventDetail)} />
                        <Route  path="/add-event" component={withRouter(AddEventForm)} />
                        <Route exact path="/" component={withRouter(EventsGrid)} />
                    </Switch>
                </div>
            {/*<EventDetail data={{eventTitle: "Deneme"}}/>*/}
        </ThemeProvider>
    );
}

export default App;
