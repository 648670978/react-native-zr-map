import React, { useRef, useEffect, Component } from 'react';
import {
    View,
    requireNativeComponent,
    UIManager, findNodeHandle, Platform,PanResponder
} from 'react-native';
import { createPosition } from './MapTool';

const ZRMapView =
    requireNativeComponent('ZRMapView');

export const MapType = {
    MAMapTypeStandard: 0,  ///< 普通地图
    MAMapTypeSatellite: 1,     ///< 卫星地图
    MAMapTypeStandardNight: 2, ///< 夜间视图
    MAMapTypeNavi: 3,          ///< 导航视图
    MAMapTypeBus: 4            ///< 公交视图
}


export class MapView extends Component {

    static defaultProps = {
        initialCoordinate: createPosition(0, 0),
        mapType: 0,
        zoomEnabled: true,
        scrollEnabled: true,
        rotateEnabled: true,
    };

    constructor(props) {
        super(props);
        this.state = {
        };
        this.mapViewRef = React.createRef()
        if (Platform.OS === 'android') {
            this.pan = PanResponder.create({
                onStartShouldSetPanResponder: () => true,
                onStartShouldSetPanResponderCapture: () => true,
                onMoveShouldSetPanResponder: () => true,
                onMoveShouldSetPanResponderCapture: () => true
            })
        }
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
        let pan = {}
        if (Platform.OS === 'android') {
            pan = {...this.pan.panHandlers}
        }
        return (
            <ZRMapView
                style={{ flex: 1 }}
                ref={this.mapViewRef}
                {...pan}
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
            [viewId, position]
        );
    }

}



