import React, {Component} from 'react';
import Grid from '@material-ui/core/Grid';
import MediaCard from "./MediaCard";
import axios from "axios";
import Box from "@material-ui/core/Box";
import Fab from "@material-ui/core/Fab";
import AddIcon from '@material-ui/icons/Add';
import {withRouter} from "react-router";

class EventsGrid extends Component {

    constructor(props) {
        super(props);
        this.state = {
            events: [],
            textToSearch : props.textToSearch
        }
    }

    fabStyle = {
        textAlign: "center",
        margin: "4vh",
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
    }

    navigateToAddEvent = () => {
        this.props.history.push('/add-event', {isUpdate: false, eventTitleToUpdate: ''});
    }

    render() {
        console.log(this.state.textToSearch);
        return (
            <div>
                <Box m={2}>
                    <Grid container spacing={2}>
                        {this.state.events.map((anEvent, index) =>
                        {
                            if (anEvent.title.includes(this.props.textToSearch)) {
                                return (
                                    <Grid item={true} xs={12} sm={6} md={3} key={index}>
                                        <MediaCard event={anEvent}/>
                                    </Grid>
                                );
                            }
                        }

                        )
                        }
                    </Grid>
                </Box>
                <Fab color="primary" aria-label="add" style={this.fabStyle}
                     onClick={this.navigateToAddEvent}>
                    <AddIcon/>
                </Fab>
            </div>
        );
    }


}

export default withRouter(EventsGrid);
