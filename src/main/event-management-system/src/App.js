import React, {Component} from 'react';
import {createMuiTheme, ThemeProvider} from "@material-ui/core/styles";
import CssBaseline from "@material-ui/core/CssBaseline";
import CustomAppBar from "./common/CustomAppBar";
import {ADMIN, EVENT_MANAGER, getIsDark, ROLE, setIsDark} from "./common/Utils";
import {withRouter} from "react-router-dom";
import {Route} from "react-router-dom";
import EventDetailForUser from "./usecases/event-detail/EventDetailForUser";
import AddEventForm from "./usecases/add-event/AddEventForm";
import WelcomePage from "./usecases/welcome-page/WelcomePage";
import LoginPage from "./usecases/login-page/LoginPage";
import EventsGridForUser from "./usecases/list-all-events/EventsGridForUser";
import EventsGridForAdmin from "./usecases/list-all-events/EventsGridForAdmin";
import EventsGridForEventManager from "./usecases/list-all-events/EventsGridForEventManager";
import EventDetailForAdmin from "./usecases/event-detail/EventDetailForAdmin";
import EventDetailForEventManager from "./usecases/event-detail/EventDetailForEventManager";
import Unauthorized from "./common/Unauthorized";
import BarChart from "./common/BarChart";
import EventStatistics from "./usecases/show-statistics/EventStatistics";


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

class App extends Component {

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
        if (localStorage.getItem("role") === "ADMIN") {
            this.props.history.push('/admin/events');
        } else if (localStorage.getItem("role") === "EVENT_MANAGER") {
            this.props.history.push('/event-manager/events');
        } else {
            this.props.history.push('/events');
        }
    }

    render() {
        return (

            <ThemeProvider theme={getIsDark() ? themeDark : themeLight}>
                <CssBaseline/>
                {this.props.location.pathname !== '/' && this.props.location.pathname !== '/login' ?
                    <CustomAppBar onModeChange={this.handleModeChange}
                                  onSearchChange={this.handleSearchChange}
                                  onDistanceChange={this.handleDistanceChange}
                                  onDateSelectionChange={this.handleDateSelectionChange}
                                  onNavigateHomePage={this.handleNavigateToHomePage}/>
                    :
                    null
                }

                <div>
                    <Route path="/events/:eventTitle" component={withRouter(EventDetailForUser)}/>
                    <Route path="/admin/events/:eventTitle"
                           component={localStorage.getItem(ROLE) === ADMIN ? withRouter(EventDetailForAdmin) : Unauthorized}/>
                    <Route path="/event-manager/events/:eventTitle"
                           component={localStorage.getItem(ROLE) === EVENT_MANAGER ? withRouter(EventDetailForEventManager) : Unauthorized}/>
                    <Route path="/add-event"
                           component={localStorage.getItem(ROLE) === ADMIN ? withRouter(AddEventForm) : Unauthorized}/>
                    <Route path="/statistics"
                           component={localStorage.getItem(ROLE) === ADMIN ? withRouter(EventStatistics) : Unauthorized}/>
                    <Route exact path="/events">
                        <EventsGridForUser textToSearch={this.state.textToSearch}
                                           distanceToLook={this.state.distanceToLook}
                                           dateToSeek={this.state.dateToSeek}/>
                    </Route>
                    <Route exact path="/admin/events">
                        {localStorage.getItem(ROLE) === ADMIN ?
                            <EventsGridForAdmin textToSearch={this.state.textToSearch}
                                                distanceToLook={this.state.distanceToLook}
                                                dateToSeek={this.state.dateToSeek}/>
                            : <Unauthorized/>
                        }
                    </Route>
                    <Route exact path="/event-manager/events">
                        {localStorage.getItem(ROLE) === EVENT_MANAGER ?
                            <EventsGridForEventManager textToSearch={this.state.textToSearch}
                                                       distanceToLook={this.state.distanceToLook}
                                                       dateToSeek={this.state.dateToSeek}/>
                            : <Unauthorized/>
                        }
                    </Route>
                    <Route path="/login" component={withRouter(LoginPage)}/>
                    <Route exact path="/" component={withRouter(WelcomePage)}/>

                </div>

            </ThemeProvider>

        );
    }
}

export default withRouter(App);
