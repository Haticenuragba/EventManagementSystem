import React, {Component} from 'react';
import EventsGrid from "./usecases/end-user/list-all-events/EventsGrid";
import {createMuiTheme, ThemeProvider} from "@material-ui/core/styles";
import CssBaseline from "@material-ui/core/CssBaseline";
import useMediaQuery from "@material-ui/core/useMediaQuery";
import {AppBar} from "@material-ui/core";
import CustomAppBar from "./common/CustomAppBar";
import {getIsDark, setIsDark} from "./common/Utils";


function App(factory, deps) {

    const theme = React.useMemo(
        () =>
            createMuiTheme({
                palette: {
                    type: getIsDark() ? "dark" : "light",
                },
            }),
    );

    const [state, setState] = React.useState({});

    const handleModeChange = (isDark) => {
        setIsDark(isDark);
        setState({});
    }

        return (
            <ThemeProvider theme={theme}>
                <CssBaseline/>
                {/*<AddEventForm data={{isUpdate:false, eventTitleToUpdate: 'Deneme'}}/>*/}
                <CustomAppBar onModeChange={handleModeChange}/>
                <EventsGrid/>
            </ThemeProvider>
        );
}

export default App;
