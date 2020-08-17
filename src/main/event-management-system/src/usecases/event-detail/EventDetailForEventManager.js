import React, {Component} from 'react';
import Card from "@material-ui/core/Card";
import CardContent from "@material-ui/core/CardContent";
import Grid from "@material-ui/core/Grid";
import axios from "axios";
import {headers} from "../../common/Utils";
import Box from "@material-ui/core/Box";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemText from "@material-ui/core/ListItemText";
import Divider from "@material-ui/core/Divider";


class EventDetailForEventManager extends Component {
    backgroundImage = require('../../images/background10.jpg');
    constructor(props) {
        super(props);
        this.state = {
            eventTitle: props.match.params.eventTitle,
            questions: []
        }
    }

    componentDidMount() {
        let auth = headers;
        axios.get("/event-managers/event/" + this.state.eventTitle, {headers: auth}).then(
            (response) => {
                if(response.status === 200){
                    let newState = this.state;
                    newState.questions = response.data;
                    this.setState({newState});
                }
            }
        )
    }


    render() {
        return (
            <div style={{
                backgroundImage: "url(" + this.backgroundImage + ")",
                backgroundRepeat: "no-repeat", backgroundAttachment: "fixed",
                backgroundPosition: "center", backgroundSize: "cover",
                minHeight: "94.2vh"
            }}>
                <Box>
                    <Grid container spacing={2} alignItems={"center"} justify={"center"}
                          style={{maxWidth: "100%"}}
                    >
                        <Grid item md={7}>
                            <Card>
                                <CardContent>
                                    <List component="nav" aria-label="mailbox folders">
                                        {this.state.questions.map((q, index) => {
                                            return (
                                                <div key={index}>
                                                <ListItem button>
                                                <ListItemText primary={q.nickname} secondary={q.question}/>
                                            </ListItem>
                                                <Divider/>
                                                </div>
                                        )

                                            ;
                                        })}
                                    </List>
                                </CardContent>
                            </Card>
                        </Grid>


                    </Grid>
                </Box>
            </div>
        );
    }
}

export default EventDetailForEventManager;
