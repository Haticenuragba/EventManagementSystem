import React, {Component, useEffect} from 'react';
import TextField from '@material-ui/core/TextField';
import Button from "@material-ui/core/Button";
import Card from "@material-ui/core/Card";
import CardActions from "@material-ui/core/CardActions";
import CardContent from "@material-ui/core/CardContent";
import Grid from "@material-ui/core/Grid";
import Typography from "@material-ui/core/Typography";
import SendIcon from '@material-ui/icons/Send';
import ClearIcon from '@material-ui/icons/Clear';
import MapView from "../../../common/MapView";

class EventDetail extends Component {

    backgroundImage = require('../../../images/background.jpg');



    constructor(props) {
        super(props);


    }

    componentDidMount() {

    }


    render() {
        return (
            <Grid container alignItems={"center"} justify={"center"}>
                <Grid item md={4} style={{padding: "1vh"}}>
                    <Card style={{height: "90vh"}}>
                        <CardContent>
                            <img src={this.backgroundImage} style={{width: "100%"}}/>
                            <MapView onLocationChange={() =>{}} data={{
                                lat: 37.022195,
                                lng: 35.293555
                            }}/>
                        </CardContent>
                    </Card>
                </Grid>
                <Grid item md={8} style={{padding: "1vh"}}>
                    <Card style={{height: "90vh"}}>
                        <CardContent>
                            <Typography variant="h5" gutterBottom>
                                Pizza Partisi
                            </Typography>
                            <Typography gutterBottom>
                                subtitle1. Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quos blanditiis
                                tenetur
                                subtitle1. Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quos blanditiis
                                tenetur
                            </Typography>
                            <br/>
                            <Typography variant="h5" gutterBottom>
                                Hemen Kaydolun
                            </Typography>
                            <div>
                                <Grid spacing={4} container>
                                    <Grid item md={6}>
                                <TextField required label="Adınız" type="text" fullWidth/>
                                    </Grid>
                                    <Grid item md={6}>
                                        <TextField required label="Soyadınız" type="text" fullWidth
                                        />
                                    </Grid>
                                </Grid>
                                <Grid spacing={4} container>
                                    <Grid item md={6}>
                                        <TextField required label="Email Adresiniz" type="email" fullWidth/>
                                    </Grid>
                                    <Grid item md={6}>
                                        <TextField required label="T.C. Kimlik Numaranız" type="number" fullWidth
                                        />
                                    </Grid>
                                </Grid>

                            </div>
                        </CardContent>
                        <CardActions>
                            <Grid container alignItems={"flex-start"} justify={"flex-end"} direction={"row"}>

                                <Grid>
                                    <Button color="secondary" variant={"contained"} endIcon={<ClearIcon/>}
                                            size={"large"}>
                                        Formu Temizle
                                    </Button>
                                </Grid>
                                <Grid>
                                    <Button color="primary" variant={"contained"}
                                            endIcon={<SendIcon/>} size={"large"}>
                                        Gönder
                                    </Button>
                                </Grid>
                            </Grid>
                        </CardActions>
                    </Card>
                </Grid>


            </Grid>
        );
    }
}

export default EventDetail;
