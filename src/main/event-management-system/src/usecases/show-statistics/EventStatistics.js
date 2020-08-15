import React, {Component} from 'react';
import Card from "@material-ui/core/Card";
import CardActions from "@material-ui/core/CardActions";
import CardContent from "@material-ui/core/CardContent";
import Grid from "@material-ui/core/Grid";
import BarChart from "../../common/BarChart";
import CanvasJSReact from '../../assets/canvasjs.react';
import {getIsDark} from "../../common/Utils";

const dateFormat = require('dateformat');

let CanvasJS = CanvasJSReact.CanvasJS;
let CanvasJSChart = CanvasJSReact.CanvasJSChart;


class EventStatistics extends Component {

    constructor(props) {
        super(props);
        this.state = {

        }
    }



    render() {
        const options = {
            title: {
                text: "Sistemde kayıtlı etkinliklerin başvuru sayısına göre dağılımı"
            },
            height: 400,
            theme: getIsDark() ? "dark2" : "light2",
            data: [{
                type: "column",
                dataPoints: [
                    { label: "Apple",  y: 10  },
                    { label: "Orange", y: 15  },
                    { label: "Banana", y: 25  },
                    { label: "Mango",  y: 30  },
                    { label: "Grape",  y: 28  },
                    { label: "Apple",  y: 10  },
                    { label: "Orange", y: 15  },
                    { label: "Banana", y: 25  },
                    { label: "Mango",  y: 30  },
                    { label: "Grape",  y: 28  },
                    { label: "Apple",  y: 10  },
                    { label: "Orange", y: 15  },
                    { label: "Banana", y: 25  },
                    { label: "Mango",  y: 30  },
                    { label: "Grape",  y: 28  },
                    { label: "Apple",  y: 10  },
                    { label: "Orange", y: 15  },
                    { label: "Banana", y: 25  },
                    { label: "Mango",  y: 30  },
                    { label: "Grape",  y: 28  }
                ]
            }]
        }

        return (
            <div>
                <Grid container alignItems={"center"} justify={"center"}>
                    <Grid item md={12} style={{padding: "1vh"}}>
                        <Card style={{height: "45vh"}}>
                            <CardContent>
                                <CanvasJSChart options = {options}
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

                            </CardContent>
                            <CardActions>

                            </CardActions>
                        </Card>
                    </Grid>
                        <Grid item md={6} style={{padding: "1vh"}}>
                            <Card style={{height: "44vh"}}>
                                <CardContent>

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
