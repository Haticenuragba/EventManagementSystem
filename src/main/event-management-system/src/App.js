import React, {Component} from 'react';
import EventsGrid from "./usecases/end-user/list-all-events/EventsGrid";
import {createMuiTheme, ThemeProvider} from "@material-ui/core/styles";
import CssBaseline from "@material-ui/core/CssBaseline";
import useMediaQuery from "@material-ui/core/useMediaQuery";
import {AppBar} from "@material-ui/core";
import CustomAppBar from "./common/CustomAppBar";
import {isDark} from "./common/Utils";


function App(factory, deps) {

    const theme = React.useMemo(
        () =>
            createMuiTheme({
                palette: {
                    type: isDark ? "dark" : "light",
                },
            }),
    );

        return (
            <ThemeProvider theme={theme}>
                <CssBaseline/>
                {/*<AddEventForm data={{isUpdate:false, eventTitleToUpdate: 'Deneme'}}/>*/}
                <CustomAppBar/>
                <EventsGrid/>
            </ThemeProvider>
        );
}

export default App;
