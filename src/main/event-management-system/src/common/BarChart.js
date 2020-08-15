import React, {Component} from "react";
import "../styles/BarChart.css"

function BarGroup(props) {
    let barPadding = 2
    let barColour = props.color;
    let widthScale = d => d * 10

    let width = widthScale(props.d.value)
    let yMid = props.barHeight * 0.5

    const handleOnClick = () => {
        props.onClick();
    }
    return <g className="bar-group">
        <rect y={barPadding * 0.5} width={width} height={props.barHeight - barPadding} fill={barColour} onClick={handleOnClick} />
        <text className="name-label" x={16} y={yMid} alignmentBaseline="middle" >{props.d.name}</text>
        <text className="value-label" x={width- 8} y={yMid} alignmentBaseline="middle" >{props.d.value}</text>
    </g>
}

class BarChart extends Component {
    constructor(props) {
        super(props);
        this.state = {
            data: [
                { name: 'Mon', value: 20 },
                { name: 'Tue', value: 40 },
                { name: 'Wed', value: 35 },
                { name: 'Thu', value: 50 },
                { name: 'Fri', value: 55 },
                { name: 'Sat', value: 40 },
                { name: 'Sun', value: 30 }
            ]
        }
    }

    handleOnClick(eventTitle){
        console.log(eventTitle);
    }

    render() {
        let barHeight = this.props.barHeight;
        let color = this.props.color;
        let barGroups = this.state.data.map((d, i) => <g transform={`translate(0, ${i * barHeight})`}>
            <BarGroup d={d} barHeight={barHeight} color={color} onClick={() => this.handleOnClick(d)}/>
        </g>)

        return <svg width={this.props.width} height={this.props.height}>
            <g className="container">
                <g transform="translate(20,20)">
                    {barGroups}
                </g>
            </g>
        </svg>
    }
}

export default BarChart;
