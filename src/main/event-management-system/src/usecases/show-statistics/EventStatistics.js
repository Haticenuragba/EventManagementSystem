import React, {Component} from 'react';
import Card from "@material-ui/core/Card";
import CardActions from "@material-ui/core/CardActions";
import CardContent from "@material-ui/core/CardContent";
import Grid from "@material-ui/core/Grid";

const dateFormat = require('dateformat');

class EventStatistics extends Component {

    constructor(props) {
        super(props);
    }



    render() {
        return (
            <div>
                <Grid container alignItems={"center"} justify={"center"}>
                    <Grid item md={4} style={{padding: "1vh"}}>
                        <Card style={{height: "90vh"}}>
                            <CardContent>

                            </CardContent>
                        </Card>
                    </Grid>
                    <Grid item md={8} style={{padding: "1vh"}}>
                        <Card style={{height: "44vh"}}>
                            <CardContent>

                            </CardContent>
                            <CardActions>

                            </CardActions>
                        </Card>
                        <br/>
                        <Card style={{height: "44vh"}}>
                            <CardContent>

                            </CardContent>
                            <CardActions>

                            </CardActions>
                        </Card>
                    </Grid>


                </Grid>
            </div>

        );
    }
}

export default EventStatistics;
