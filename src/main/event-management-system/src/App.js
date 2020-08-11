import React from 'react';
import {createMuiTheme, ThemeProvider} from "@material-ui/core/styles";
import CssBaseline from "@material-ui/core/CssBaseline";
import CustomAppBar from "./common/CustomAppBar";
import {getIsDark, setIsDark} from "./common/Utils";
import EventDetail from "./usecases/end-user/event-application/EventDetail";


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
            <CustomAppBar onModeChange={handleModeChange}/>
            {/*<AddEventForm data={{isUpdate:false, eventTitleToUpdate: 'Deneme'}}/>*/}
            <EventDetail data={{eventTitle: "Deneme"}}/>
        </ThemeProvider>
    );
}

export default App;
