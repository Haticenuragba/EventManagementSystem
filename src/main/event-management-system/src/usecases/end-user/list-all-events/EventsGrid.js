import React, {Component} from 'react';
import Grid from '@material-ui/core/Grid';
import MediaCard from "./MediaCard";
import axios from "axios";
import Box from "@material-ui/core/Box";
import Fab from "@material-ui/core/Fab";
import AddIcon from '@material-ui/icons/Add';

export default class EventsGrid extends Component{
    constructor(props) {
        super(props);
        this.state = {
            events: []
        }
    }

    fabStyle = {
        borderTop: "1px solid #E7E7E7",
        textAlign: "center",
        padding: "20px",
        margin: "20px",
        position: "fixed",
        right: "0",
        bottom: "0",
        zIndex: 100
    }

    componentDidMount() {
        axios.get("/events")
            .then(response => {
                this.setState({events: response.data})
            });
        console.log("geldi");
    }

    navigateToAddEvent = (title) => {
        this.props.history.push('/add-event', {isUpdate: false, eventTitleToUpdate: ''});
    }

    render() {
        return (
            <div>
            <Box m={2} style={{width:"90vw", height: "80vh"}}>
            <Grid container spacing={2}>
                {this.state.events.map(anEvent => (
                    <Grid item xs={12} sm={6} md={3}>
                        <MediaCard event={anEvent}/>
                    </Grid>
                ))
                }
            </Grid>
            </Box>
                <Fab color="primary" aria-label="add" style={this.fabStyle}>
                    <AddIcon onClick={this.navigateToAddEvent} />
                </Fab>
            </div>
        );
    }



}
