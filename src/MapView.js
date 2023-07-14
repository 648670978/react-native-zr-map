import React, { useRef, useEffect, Component } from 'react';
import {
    View,
    requireNativeComponent,
    UIManager, findNodeHandle
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
        UIManager.dispatchViewManagerCommand(
            findNodeHandle(this),
            UIManager.getViewManagerConfig('ZRMapView').Commands
              .setCameraPosition,
            [position]
          );
    }

}



