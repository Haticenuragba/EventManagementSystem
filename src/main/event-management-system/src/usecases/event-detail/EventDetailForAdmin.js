import React, {Component} from 'react';
import TextField from '@material-ui/core/TextField';
import Button from "@material-ui/core/Button";
import Card from "@material-ui/core/Card";
import CardActions from "@material-ui/core/CardActions";
import CardContent from "@material-ui/core/CardContent";
import Grid from "@material-ui/core/Grid";
import Typography from "@material-ui/core/Typography";
import ClearIcon from '@material-ui/icons/Clear';
import MapView from "../../common/MapView";
import axios from "axios";
import EditIcon from '@material-ui/icons/Edit';
import EqualizerIcon from '@material-ui/icons/Equalizer';
import LinearProgress from "@material-ui/core/LinearProgress";
import Box from "@material-ui/core/Box";

const dateFormat = require('dateformat');

class EventDetailForAdmin extends Component {

    eventTitle = '';
    emptyApplication = {
        idNumber: "",
        email: "",
        name: "",
        surname: "",
        attendantNumber: "",
        applicationCustomAttributes: []
    }


    constructor(props) {
        super(props);
        this.eventTitle = props.match.params.eventTitle;
        this.state = {
            event: {
                customAttributes: [{}]
            },
            application: {...this.emptyApplication}
        }
    }

    componentWillMount() {
        axios.get("/events/" + this.eventTitle)
            .then(response => {
                if (response.status === 200) {
                    let newState = this.state;
                    newState.event = response.data;
                    this.setState({
                        newState
                    });
                    console.log(this.state.event);
                }
            })
            .catch(error => {
                console.log(error.response);
            })
    }

    handleLocation = (lat, lng) => {
    }

    navigateToUpdateEvent = (title) => {
        this.props.history.push('/add-event', {isUpdate: true, eventTitleToUpdate: title});
    }



    render() {
        return (
            <div>
                <Grid container alignItems={"center"} justify={"center"}>
                    <Grid item md={4} style={{padding: "1vh"}}>
                        <Card style={{height: "90vh"}}>
                            <CardContent>
                                <img src={this.state.event.image} style={{width: "100%"}}/>
                                <br/><br/>
                                <MapView onLocationChange={this.handleLocation}
                                         data={{
                                             lat: this.state.event.latitude,
                                             lng: this.state.event.longitude,
                                             isConstant: true
                                         }}/>
                            </CardContent>
                        </Card>
                    </Grid>
                    <Grid item md={8} style={{padding: "1vh"}}>
                        <Card style={{height: "90vh"}}>
                            <CardContent>
                                <Typography variant="h5" gutterBottom>
                                    {this.state.event.title}
                                </Typography>
                                <Typography gutterBottom>
                                    {this.state.event.description}
                                </Typography>
                                <br/>
                                <Typography gutterBottom>
                                    Etkinlik {dateFormat(this.state.event.startDate, "dd/mm/yyyy")} - {dateFormat(this.state.event.endDate, "dd/mm/yyyy")} tarihleri
                                    arasında gerçekleşecektir.
                                </Typography>

                                <br/>
                                <Grid container spacing={2}>
                                    <Grid item>
                                        <Typography>Kaydolan Kişi: {this.state.event.attendantNumber}</Typography>
                                    </Grid>
                                    <Grid item md={9}>
                                        <Box p={1}>
                                            <LinearProgress variant={"determinate"}
                                                            value={(this.state.event.attendantNumber / this.state.event.quota) * 100}/>
                                        </Box>
                                    </Grid>
                                    <Grid item>
                                        <Typography>Kalan
                                            Kontenjan: {this.state.event.quota - this.state.event.attendantNumber}</Typography>
                                    </Grid>
                                </Grid>
                                <br/>
                                <Typography variant="h5" gutterBottom>
                                   Kayıt Formu Ön İzleme
                                </Typography>
                                <form ref={(el) => this.applicationFormRef = el}>
                                    <div>
                                        <Grid spacing={4} container>
                                            <Grid item md={6}>
                                                <TextField name="name"
                                                           disabled
                                                           label="Adınız"
                                                           type="text" fullWidth/>
                                            </Grid>
                                            <Grid item md={6}>
                                                <TextField name="surname"
                                                           disabled
                                                           label="Soyadınız" type="text" fullWidth
                                                />
                                            </Grid>
                                        </Grid>
                                        <Grid spacing={4} container>
                                            <Grid item md={6}>
                                                <TextField name="email"
                                                           disabled
                                                           label="Email Adresiniz" type="email" fullWidth/>
                                            </Grid>
                                            <Grid item md={6}>
                                                <TextField name="idNumber"
                                                           disabled
                                                           label="T.C. Kimlik Numaranız" type="number" fullWidth
                                                />
                                            </Grid>
                                        </Grid>

                                        <div>
                                            {
                                                this.state.event.customAttributes.map((customAttribute, index) => (
                                                    <div key={index}>
                                                        <Grid container spacing={4}>
                                                            <Grid item md={12}>
                                                                <TextField
                                                                    InputLabelProps={customAttribute.type === "date" ? {shrink: true} : {}}
                                                                   disabled
                                                                    type={customAttribute.type}
                                                                    fullWidth
                                                                    label={customAttribute.question}
                                                                />
                                                            </Grid>
                                                        </Grid>
                                                    </div>
                                                ))
                                            }
                                        </div>

                                    </div>
                                </form>
                            </CardContent>
                            <CardActions>
                                <Grid container alignItems={"flex-start"} justify={"flex-end"} direction={"row"}>

                                    <Grid>
                                        <Button color="primary" variant={"contained"}
                                                onClick={() => this.navigateToUpdateEvent(this.eventTitle)}
                                                endIcon={<EditIcon/>} size={"large"}>
                                            ETKİNLİĞİ DÜZENLE
                                        </Button>
                                    </Grid>
                                </Grid>
                            </CardActions>
                        </Card>
                    </Grid>


                </Grid>
            </div>

        );
    }
}

export default EventDetailForAdmin;
