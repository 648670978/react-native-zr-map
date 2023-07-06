import React, { useRef, useEffect, Component } from 'react';
import {
    View,
    requireNativeComponent,
    UIManager, findNodeHandle
} from 'react-native';

const RNZrMapViewMan =
    requireNativeComponent('RNZrMapViewMan');

const createFragment = (viewId) => {
    UIManager.dispatchViewManagerCommand(
        viewId,
        // we are calling the 'create' command
        UIManager.RNZrMapViewMan.Commands.create.toString(),
        [viewId],
    );
}

export const MapView = () => {
    const ref = useRef(null);

    useEffect(() => {
        const viewId = findNodeHandle(ref.current);
        createFragment(viewId);
    }, []);

    return (
        <RNZrMapViewMan
            style={{ flex: 1 }}
            ref={ref}
        />
    );
};
