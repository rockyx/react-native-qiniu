Pod::Spec.new do |s|

  s.name            = "react-native-qiniu"
  s.version         = "0.0.1"
  s.summary         = "A React Native module for Qiniu"
  s.author          = { 'Rocky Tsui' => 'wesharer@gmail.com' }
  s.license         = "MIT"
  s.homepage        = "https://github.com/rockyx/react-native-qiniu"
  s.source          = { :git => 'https://github.com/rockyx/react-native-qiniu', :tag => "v#{s.version}"}
  s.platform        = :ios, '9.0'
  s.source_files    = "ios/RNQiniu/*.{h,m}"
  s.preserve_paths  = '*.js'
  s.dependency 'React'
  s.dependency 'Qiniu', '~> 7.1'
end
