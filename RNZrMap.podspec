require 'json'

package = JSON.parse(File.read(File.join(__dir__, 'package.json')))


Pod::Spec.new do |s|
  s.name         = "RNZrMap"
  s.version        = package['version'].gsub(/v|-beta/, '')
  s.summary        = package['description']
  s.author         = package['author']
  s.license        = package['license']
  s.homepage       = package['homepage']

  s.source         = { :git => 'https://github.com/648670978/react-native-zr-map.git', :tag => "v#{s.version}"}
  s.source_files = 'ios/*.{h,m}'
  s.ios.deployment_target = '9.0'

  s.dependency 'React-Core'
  s.dependency 'AMap3DMap-NO-IDFA', '9.6.0'
  # s.dependency 'AMap2DMap', '5.6.1'
  s.dependency 'AMapSearch-NO-IDFA', '9.5.0'
  s.dependency 'AMapLocation-NO-IDFA', '2.9.0'

end

  