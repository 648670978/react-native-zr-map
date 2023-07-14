import React, { useRef, useEffect, Component } from 'react';
import {
    View,
    requireNativeComponent,
    UIManager, findNodeHandle,Platform
} from 'react-native';

const ZRMapView =
    requireNativeComponent('ZRMapView');

export class MapView extends Component {
    constructor(props) {
        super(props);
        this.state = {
        };
        this.mapViewRef = React.createRef()
    }

    componentDidMount() {
        if (Platform.OS == 'android') {
            const viewId = findNodeHandle(this.mapViewRef.current);
            UIManager.dispatchViewManagerCommand(
                viewId,
                UIManager.ZRMapView.Commands.create.toString(), // we are calling the 'create' command
                [viewId]
              );
        }
    }

    render() {
        return (
            <ZRMapView
                style={{ flex: 1 }}
                ref={this.mapViewRef}
                {...this.props}
            />
        );
    }
    //{latitude:39.993207,longitude:116.473115}
    setCameraPosition(position) {
        const viewId = findNodeHandle(this.mapViewRef.current);
        UIManager.dispatchViewManagerCommand(
            findNodeHandle(this),
            UIManager.getViewManagerConfig('ZRMapView').Commands
              .setCameraPosition.toString(),
            [viewId,position]
          );
    }

}



