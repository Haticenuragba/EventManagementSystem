import React, {Component} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Grid from '@material-ui/core/Grid';
import EventsInCards from "./EventsInCards";
import MediaCard from "./MediaCard";
import axios from "axios";

export default class EventsGrid extends Component{
    constructor(props) {
        super(props);
        this.state = {
            events: []
        }
    }

    componentDidMount() {
        axios.get("/events")
            .then(response => {
                this.setState({events: response.data})
            });
        console.log("geldi");
    }
    render() {
        return (
            <div>
            <Grid container spacing={2}>
                {this.state.events.map(anEvent => (
                    <Grid item xs={12} sm={6} md={3}>
                        <MediaCard event={anEvent}/>
                    </Grid>
                ))
                }
            </Grid>
            </div>
        );
    }



}
