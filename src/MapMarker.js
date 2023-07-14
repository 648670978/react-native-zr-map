import React, { useRef, useEffect, Component } from 'react';
import {
    View,
    requireNativeComponent,
    UIManager, findNodeHandle
} from 'react-native';

const ZRMapMarker =
    requireNativeComponent('ZRMapMarker');

export class MapMarker extends Component {

    static defaultProps = {
        title:'',
        subtitle:'',
        position:{}
      };

    constructor(props) {
        super(props);
        this.state = {
        };
    }

    render() {
        return (
            <ZRMapMarker
                {...props}
            />
        );
    }
}
