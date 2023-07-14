import React, { useRef, useEffect, Component } from 'react';
import {
    View,
    requireNativeComponent,
    UIManager, findNodeHandle
} from 'react-native';
import { createPosition } from './MapTool';

const ZRMapMarker =
    requireNativeComponent('ZRMapMarker');

export class MapMarker extends Component {

    static defaultProps = {
        title:'',
        subtitle:'',
        coordinate:createPosition(0,0)
      };

    constructor(props) {
        super(props);
        this.state = {
        };
        console.log('props',this.props);
    }

    render() {
        return (
            <ZRMapMarker
                {...this.props}
            />
        );
    }
}
