import React from 'react';
import {createMuiTheme, ThemeProvider} from "@material-ui/core/styles";
import CssBaseline from "@material-ui/core/CssBaseline";
import CustomAppBar from "./common/CustomAppBar";
import {getIsDark, setIsDark} from "./common/Utils";
import AddEventForm from "./usecases/admin/add-event/AddEventForm";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import EventDetail from "./usecases/end-user/event-application/EventDetail";
import EventsGrid from "./usecases/end-user/list-all-events/EventsGrid";
import Fab from "@material-ui/core/Fab";
import AddIcon from "@material-ui/icons/Add";


function App(factory, deps) {

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



    return (
        <ThemeProvider theme={theme}>
            <CssBaseline/>
            <CustomAppBar onModeChange={handleModeChange}/>
            <Router>
                <div>
                    <Switch>
                        <Route exact path="/" component={EventsGrid} />
                        <Route path="/events/:eventTitle" component={EventDetail} />
                        <Route path="/add-event" component={AddEventForm} />
                    </Switch>
                </div>
            </Router>
            {/*<EventDetail data={{eventTitle: "Deneme"}}/>*/}
        </ThemeProvider>
    );
}

export default App;
