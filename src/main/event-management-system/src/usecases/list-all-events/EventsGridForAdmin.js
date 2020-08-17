import React, {Component} from 'react';
import Grid from '@material-ui/core/Grid';
import MediaCard from "./MediaCard";
import axios from "axios";
import BarChartIcon from '@material-ui/icons/BarChart';
import Box from "@material-ui/core/Box";
import Fab from "@material-ui/core/Fab";
import PostAddIcon from '@material-ui/icons/PostAdd';
import PersonAddIcon from '@material-ui/icons/PersonAdd';

import {withRouter} from "react-router";
import {
    dateStringToObject,
    getDaysLaterInMilliseconds,
    getDistanceFromLatLonInKm
} from "../../common/Utils";
import {startNotificationService} from "../push-notification/PushNotification";

class EventsGridForAdmin extends Component {

    constructor(props) {
        super(props);
        this.state = {
            events: [],
            textToSearch: props.textToSearch,
            distanceToLook: props.distanceToLook,
            dateToSeek: props.dateToSeek
        }
    }

    addFabStyle = {
        textAlign: "center",
        margin: "2vh",
        position: "fixed",
        marginBottom: "10vh",
        right: "0",
        bottom: "0",
        zIndex: 100
    };

    statisticsFabStyle = {
        textAlign: "center",
        margin: "2vh",
        position: "fixed",
        right: "0",
        bottom: "0",
        zIndex: 100
    };

    addManagerFabStyle = {
        textAlign: "center",
        margin: "2vh",
        marginRight: "10vh",
        position: "fixed",
        right: "0",
        bottom: "0",
        zIndex: 100
    };

    componentDidMount() {
        axios.get("/events")
            .then(response => {
                this.setState({events: response.data})
            });

        startNotificationService();
    }


    navigateToAddEvent = () => {
        this.props.history.push('/add-event', {isUpdate: false, eventTitleToUpdate: ''});
    }
    navigateToStatistics = () => {
        this.props.history.push('/statistics');
    }
    navigateToAddManager = () => {
        this.props.history.push('/add-manager');
    }

    filterByTitle(event) {
        return event.title.toLowerCase().includes(this.props.textToSearch.toLowerCase());
    }

    filterByLocation(event) {
        let value = getDistanceFromLatLonInKm(37.022195, 35.293555, event.latitude, event.longitude)
        if (value <= this.props.distanceToLook)
            return true;
        else
            return false;
    }

    filterByDate(event) {
        switch (this.props.dateToSeek) {
            case 0: return true;
            case 1: return getDaysLaterInMilliseconds(7) >= dateStringToObject(event.startDate).getTime();
            case 2: return getDaysLaterInMilliseconds(30) >= dateStringToObject(event.startDate).getTime();
            case 3: return true;
            default: return true;
        }
    }


    render() {
        return (
            <div>
                <Box m={2}>
                    <Grid container spacing={2}>
                        {this.state.events.map((anEvent, index) => {
                                if (this.filterByTitle(anEvent) && this.filterByLocation(anEvent) && this.filterByDate(anEvent)) {
                                    return (
                                        <Grid item={true} xs={12} sm={6} md={3} key={index}>
                                            <MediaCard event={anEvent}/>
                                        </Grid>
                                    );
                                }
                                else{
                                    return null;
                                }
                            }
                        )
                        }
                    </Grid>
                </Box>
                <Fab color="primary" aria-label="add" style={this.addManagerFabStyle}
                     onClick={this.navigateToAddManager}>
                    <PersonAddIcon/>
                </Fab>
                <Fab color="primary" aria-label="add" style={this.addFabStyle}
                     onClick={this.navigateToAddEvent}>
                    <PostAddIcon/>
                </Fab>
                <Fab color="secondary" aria-label="add" style={this.statisticsFabStyle}
                     onClick={this.navigateToStatistics}>
                    <BarChartIcon/>
                </Fab>
            </div>
        );
    }


}

export default withRouter(EventsGridForAdmin);
