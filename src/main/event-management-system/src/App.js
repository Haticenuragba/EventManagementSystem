import React, {Component} from 'react';
import {createMuiTheme, ThemeProvider} from "@material-ui/core/styles";
import CssBaseline from "@material-ui/core/CssBaseline";
import CustomAppBar from "./common/CustomAppBar";
import {getIsDark, setIsDark} from "./common/Utils";
import EventsGrid from "./usecases/end-user/list-all-events/EventsGrid";
import {withRouter} from "react-router-dom";
import {Route} from "react-router-dom";
import EventDetail from "./usecases/end-user/event-application/EventDetail";
import AddEventForm from "./usecases/admin/add-event/AddEventForm";


const themeDark = createMuiTheme({
    palette: {
        type: "dark"
    },
});

const themeLight = createMuiTheme({
    palette: {
        type: "light"
    },
});
class App extends Component{

    constructor(props) {
        super(props);

        this.state = {
            textToSearch: ''
        }
    }

    handleModeChange = (isDark) => {
        setIsDark(isDark);
        let newState = this.state;
        this.setState(newState);
    }

    handleSearchChanged = (text) => {
        let newState = this.state;
        newState.textToSearch = text;
        this.setState(newState);
    }

    handleNavigateToHomePage = () => {
        this.props.history.push('/');
    }

    render() {
        return (

            <ThemeProvider theme={getIsDark() ? themeDark : themeLight}>
                <CssBaseline/>
                <CustomAppBar onModeChange={this.handleModeChange}
                              onSearchChanged={this.handleSearchChanged}
                              onNavigateHomePage={this.handleNavigateToHomePage}/>
                <div>
                    <Route  path="/events/:eventTitle" component={withRouter(EventDetail)} />
                    <Route  path="/add-event" component={withRouter(AddEventForm)} />
                    <Route exact path="/" >
                        <EventsGrid textToSearch={this.state.textToSearch}/>
                    </Route>

                </div>
                {/*<EventDetail data={{eventTitle: "Deneme"}}/>*/}
            </ThemeProvider>

        );
    }
}

export default withRouter(App);
