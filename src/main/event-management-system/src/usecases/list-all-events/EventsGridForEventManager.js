import React, {Component} from 'react';
import Grid from '@material-ui/core/Grid';
import MediaCard from "./MediaCard";
import axios from "axios";
import Box from "@material-ui/core/Box";
import {withRouter} from "react-router";
import {
    dateStringToObject,
    getDaysLaterInMilliseconds,
    getDistanceFromLatLonInKm, headers, USERNAME
} from "../../common/Utils";

class EventsGridForEventManager extends Component {

    constructor(props) {
        super(props);
        this.state = {
            events: [],
            textToSearch: props.textToSearch,
            distanceToLook: props.distanceToLook,
            dateToSeek: props.dateToSeek
        }
    }

    componentDidMount() {
        let managerName = localStorage.getItem(USERNAME);
        axios.get("/event-managers/" + managerName, {headers: headers})
            .then(response => {
                this.setState({events: response.data.events});
            });
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
            </div>
        );
    }


}

export default withRouter(EventsGridForEventManager);
