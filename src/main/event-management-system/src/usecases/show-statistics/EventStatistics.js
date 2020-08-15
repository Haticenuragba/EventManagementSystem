import React, {Component} from 'react';
import Card from "@material-ui/core/Card";
import CardActions from "@material-ui/core/CardActions";
import CardContent from "@material-ui/core/CardContent";
import Grid from "@material-ui/core/Grid";
import BarChart from "../../common/BarChart";
import CanvasJSReact from '../../assets/canvasjs.react';
import {getIsDark, headers, showErrorDialog, showSuccessDialog} from "../../common/Utils";
import TableContainer from "@material-ui/core/TableContainer";
import Paper from "@material-ui/core/Paper";
import Table from "@material-ui/core/Table";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import TableCell from "@material-ui/core/TableCell";
import TableBody from "@material-ui/core/TableBody";
import axios from "axios";

const dateFormat = require('dateformat');

let CanvasJS = CanvasJSReact.CanvasJS;
let CanvasJSChart = CanvasJSReact.CanvasJSChart;


class EventStatistics extends Component {

    constructor(props) {
        super(props);
        this.state = {
            dataPointsForEvent: [
            ],
            dataPointsForApplication: [

            ]
        }
    }

    componentDidMount() {
        axios.get("/statistics/by-attendant-number", {headers: headers})
            .then(response => {
                let newState = this.state;
                newState.dataPointsForEvent = response.data;
                this.setState({newState});
                this.getDataForApplication(this.state.dataPointsForEvent[0].label);
            })
            .catch(error => {
                if (error.response.data.status === 406)
                    showErrorDialog(error.response.data.message);
                else
                    showErrorDialog("Bir hata oluştu");
            });

    }

    onClickEvent = (e) => {
        this.getDataForApplication(e.dataPoint.label);
    }

    getDataForApplication = (title) => {
        axios.get("/statistics/" + title + "/by-date", {headers: headers})
            .then(response => {
                let newState = this.state;
                newState.dataPointsForApplication = response.data;
                this.setState({newState});
            })
            .catch(error => {
                if (error.response.data.status === 406)
                    showErrorDialog(error.response.data.message);
                else
                    showErrorDialog("Bir hata oluştu");
            });
    }


    createData(name, calories, fat, carbs, protein) {
        return { name, calories, fat, carbs, protein };
    }

     rows = [
        this.createData('Frozen yoghurt', 159, 6.0, 24, 4.0),
        this.createData('Ice cream sandwich', 237, 9.0, 37, 4.3),
        this.createData('Eclair', 262, 16.0, 24, 6.0),
        this.createData('Cupcake', 305, 3.7, 67, 4.3),
        this.createData('Gingerbread', 356, 16.0, 49, 3.9),
         this.createData('Frozen yoghurt', 159, 6.0, 24, 4.0),
         this.createData('Ice cream sandwich', 237, 9.0, 37, 4.3),
         this.createData('Eclair', 262, 16.0, 24, 6.0),
         this.createData('Cupcake', 305, 3.7, 67, 4.3),
         this.createData('Gingerbread', 356, 16.0, 49, 3.9),
    ];


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
                text: "etkinliğinin günlere göre başvuru dağılımı",
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

        return (
            <div>
                <Grid container alignItems={"center"} justify={"center"}>
                    <Grid item md={12} style={{padding: "1vh"}}>
                        <Card style={{height: "45vh"}}>
                            <CardContent>
                                <CanvasJSChart options = {optionsForEvent}
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
                                <CanvasJSChart options = {optionsForApplication}
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
                                    <TableContainer component={Paper} style={{maxHeight:"40vh"}}>
                                        <Table aria-label="simple table">
                                            <TableHead>
                                                <TableRow>
                                                    <TableCell>T.C. Kimlik No</TableCell>
                                                    <TableCell align="right">İsim</TableCell>
                                                    <TableCell align="right">Soyisim</TableCell>
                                                    <TableCell align="right">Email</TableCell>
                                                </TableRow>
                                            </TableHead>
                                            <TableBody>
                                                {this.rows.map((row) => (
                                                    <TableRow key={row.name}>
                                                        <TableCell component="th" scope="row">
                                                            {row.name}
                                                        </TableCell>
                                                        <TableCell align="right">{row.calories}</TableCell>
                                                        <TableCell align="right">{row.fat}</TableCell>
                                                        <TableCell align="right">{row.carbs}</TableCell>
                                                    </TableRow>
                                                ))}
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
    }
}

export default EventStatistics;
