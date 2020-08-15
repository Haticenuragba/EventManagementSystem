import React, {Component} from 'react';
import {createMuiTheme, ThemeProvider} from "@material-ui/core/styles";
import CssBaseline from "@material-ui/core/CssBaseline";
import CustomAppBar from "./common/CustomAppBar";
import {getIsDark, setIsDark} from "./common/Utils";
import {withRouter} from "react-router-dom";
import {Route} from "react-router-dom";
import EventDetail from "./usecases/event-application/EventDetail";
import AddEventForm from "./usecases/add-event/AddEventForm";
import WelcomePage from "./usecases/welcome-page/WelcomePage";
import LoginPage from "./usecases/login-page/LoginPage";
import EventsGridForUser from "./usecases/list-all-events/EventsGridForUser";
import EventsGridForAdmin from "./usecases/list-all-events/EventsGridForAdmin";
import EventsGridForEventManager from "./usecases/list-all-events/EventsGridForEventManager";


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
            textToSearch: '',
            distanceToLook: 15000,
            dateToSeek: 0
        }
    }

    handleModeChange = (isDark) => {
        setIsDark(isDark);
        let newState = this.state;
        this.setState(newState);
    }

    handleSearchChange = (text) => {
        let newState = this.state;
        newState.textToSearch = text;
        this.setState(newState);
    }

    handleDistanceChange = (distance) => {
        let newState = this.state;
        newState.distanceToLook = distance;
        this.setState(newState);
    }

    handleDateSelectionChange = (date) => {
        let newState = this.state;
        newState.dateToSeek = date;
        this.setState(newState);
    }

    handleNavigateToHomePage = () => {
        this.props.history.push('/');
    }

    render() {
        return (

            <ThemeProvider theme={getIsDark() ? themeDark : themeLight}>
                <CssBaseline/>
                {this.props.location.pathname !== '/' && this.props.location.pathname !== '/login'?
                    <CustomAppBar onModeChange={this.handleModeChange}
                                  onSearchChange={this.handleSearchChange}
                                  onDistanceChange={this.handleDistanceChange}
                                  onDateSelectionChange={this.handleDateSelectionChange}
                                  onNavigateHomePage={this.handleNavigateToHomePage}/>
                                  :
                    null
                }

                <div>
                    <Route  path="/events/:eventTitle" component={withRouter(EventDetail)} />
                    <Route  path="/add-event" component={withRouter(AddEventForm)} />
                    <Route exact path="/events" >
                        <EventsGridForUser textToSearch={this.state.textToSearch} distanceToLook={this.state.distanceToLook} dateToSeek={this.state.dateToSeek}/>
                    </Route>
                    <Route exact path="/admin/events" >
                        <EventsGridForAdmin textToSearch={this.state.textToSearch} distanceToLook={this.state.distanceToLook} dateToSeek={this.state.dateToSeek}/>
                    </Route>
                    <Route exact path="/event-manager/events" >
                        <EventsGridForEventManager textToSearch={this.state.textToSearch} distanceToLook={this.state.distanceToLook} dateToSeek={this.state.dateToSeek}/>
                    </Route>
                    <Route  path="/login" component={withRouter(LoginPage)} />
                    <Route exact path="/" component={withRouter(WelcomePage)} />

                </div>

                {/*<EventDetail data={{eventTitle: "Deneme"}}/>*/}
            </ThemeProvider>

        );
    }
}

export default withRouter(App);
