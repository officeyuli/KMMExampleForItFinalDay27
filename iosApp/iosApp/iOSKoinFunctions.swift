//
//  iOSKoinFunctions.swift
//  iosApp
//
//  Created by yuli on 2021/10/11.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared
func startKoin(){
    let _userDefaults = UserDefaults(suiteName: "KMMItExample")!
    let _doONStartup = {NSLog("Hello from iOS/Swift")}
    let koinApplication = KoinForIOSKt.doInitKoinIos(userDefaults: _userDefaults,doOnStartup: _doONStartup)
    _koin = koinApplication.koin
}
private var _koin: Koin_coreKoin? = nil

var koin: Koin_coreKoin {
    return _koin!
}
