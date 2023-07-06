import { NativeModules } from "react-native";

const { RNZrMap } = NativeModules;

export function initMap(apiKey) {
    RNZrMap.initMap(apiKey)
}


