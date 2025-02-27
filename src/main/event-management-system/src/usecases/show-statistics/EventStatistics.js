import React, {Component} from 'react';
import Card from "@material-ui/core/Card";
import CardActions from "@material-ui/core/CardActions";
import CardContent from "@material-ui/core/CardContent";
import Grid from "@material-ui/core/Grid";
import CanvasJSReact from '../../assets/canvasjs.react';
import {getIsDark, headers, showErrorDialog} from "../../common/Utils";
import TableContainer from "@material-ui/core/TableContainer";
import Paper from "@material-ui/core/Paper";
import Table from "@material-ui/core/Table";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import TableCell from "@material-ui/core/TableCell";
import TableBody from "@material-ui/core/TableBody";
import axios from "axios";
import Typography from "@material-ui/core/Typography";

let CanvasJSChart = CanvasJSReact.CanvasJSChart;


class EventStatistics extends Component {

    constructor(props) {
        super(props);
        this.state = {
            dataPointsForEvent: [],
            dataPointsForApplication: [],
            attendants: [],
            selectedEventTitle: ""
        }
    }

    componentDidMount() {
        let auth = headers;
        axios.get("/statistics/by-attendant-number", {headers: auth})
            .then(response => {
                let newState = this.state;
                newState.dataPointsForEvent = response.data;
                newState.selectedEventTitle = newState.dataPointsForEvent[0].label;
                this.setState({newState});
                this.getDataForApplication(this.state.selectedEventTitle);
                this.getAttendantsForApplication(this.state.selectedEventTitle);
            })
            .catch(error => {
                    console.log(error);
            });

    }

    onClickEvent = (e) => {
        let newState = this.state;
        newState.selectedEventTitle = e.dataPoint.label;
        this.setState({newState});
        this.getDataForApplication(this.state.selectedEventTitle);
        this.getAttendantsForApplication(this.state.selectedEventTitle);
    }

    getDataForApplication = (title) => {
        let auth = headers;
        axios.get("/statistics/" + title + "/by-date", {headers: auth})
            .then(response => {
                let newState = this.state;
                newState.dataPointsForApplication = response.data;
                this.setState({newState});
            })
            .catch(error => {
                    showErrorDialog("Bir hata oluştu");
            });
    }

    getAttendantsForApplication = (title) => {
        let auth = headers;
        axios.get("/statistics/" + title + "/attendants", {headers: auth})
            .then(response => {
                let newState = this.state;
                newState.attendants = response.data;
                this.setState({newState});
            })
            .catch(error => {
                    showErrorDialog("Bir hata oluştu");
            });
    }


    render() {
        const optionsForEvent = {
            title: {
                text: "Sistemde kayıtlı etkinliklerin başvuru sayısına göre dağılımı",
                fontSize: 32
            },
            height: 400,
            theme: getIsDark() ? "dark2" : "light2",
            data: [{
                color: "#c51162",
                type: "column",
                click: this.onClickEvent,
                dataPoints: this.state.dataPointsForEvent
            }]
        }

        const optionsForApplication = {
            title: {
                text: this.state.selectedEventTitle + " etkinliğinin günlere göre başvuru dağılımı",
                fontSize: 24
            },
            height: 400,
            theme: getIsDark() ? "dark2" : "light2",
            data: [{
                type: "column",
                color: '#7986cb',
                dataPoints: this.state.dataPointsForApplication
            }]
        }
        if (this.state.dataPointsForEvent.length > 0) {
            return (
                <div>
                    <Grid container alignItems={"center"} justify={"center"}>
                        <Grid item md={12} style={{padding: "1vh"}}>
                            <Card style={{height: "45vh"}}>
                                <CardContent>
                                    <CanvasJSChart options={optionsForEvent}
                                        /* onRef = {ref => this.chart = ref} */
                                    />
                                </CardContent>
                            </Card>
                        </Grid>
                        <br/>
                        <Grid container>
                            <Grid item md={6} style={{padding: "1vh"}}>
                                <Card style={{height: "44vh"}}>
                                    <CardContent>
                                        <CanvasJSChart options={optionsForApplication}
                                            /* onRef = {ref => this.chart = ref} */
                                        />
                                    </CardContent>
                                    <CardActions>

                                    </CardActions>
                                </Card>
                            </Grid>
                            <Grid item md={6} style={{padding: "1vh"}}>
                                <Card style={{height: "44vh"}}>
                                    <CardContent>
                                        <TableContainer component={Paper} style={{maxHeight: "40vh"}}>
                                            <Table aria-label="simple table">
                                                <TableHead>
                                                    <TableRow>
                                                        <TableCell>T.C. Kimlik No</TableCell>
                                                        <TableCell align="right">İsim</TableCell>
                                                        <TableCell align="right">Soy isim</TableCell>
                                                        <TableCell align="right">Email</TableCell>
                                                    </TableRow>
                                                </TableHead>
                                                <TableBody>
                                                    {this.state.attendants.length > 0 ?
                                                        this.state.attendants.map((row) => (
                                                            <TableRow key={row.idNumber}>
                                                                <TableCell component="th" scope="row">
                                                                    {row.idNumber}
                                                                </TableCell>
                                                                <TableCell align="right">{row.name}</TableCell>
                                                                <TableCell align="right">{row.surname}</TableCell>
                                                                <TableCell align="right">{row.email}</TableCell>
                                                            </TableRow>
                                                        ))
                                                        :
                                                        <Grid style={{padding: "2vh"}} alignContent={"center"}
                                                              justify={"center"}><Grid item><Typography
                                                            variant={"subtitle2"}>Etkinliğe kimse başvuru
                                                            yapmadı.</Typography></Grid></Grid>
                                                    }
                                                </TableBody>
                                            </Table>
                                        </TableContainer>
                                    </CardContent>
                                    <CardActions>

                                    </CardActions>
                                </Card>
                            </Grid>

                        </Grid>

                    </Grid>
                </div>

            );
        } else {
            return <Grid container alignContent={"center"} justify={"center"}>
                <Typography style={{marginTop: "40vh"}}>İstatistik bulunamadı</Typography>
            </Grid>
        }
    }
}

export default EventStatistics;
