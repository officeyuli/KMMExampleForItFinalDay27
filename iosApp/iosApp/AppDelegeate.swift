//
//  AppDelegeate.swift
//  iosApp
//
//  Created by yuli on 2021/10/11.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import UIKit
import SwiftUI

@UIApplicationMain
class  AppDelegate: UIResponder, UIApplicationDelegate{
    var window: UIWindow?

    func application(_ application: UIApplication, didFinishLaunchingWithOptions
                launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
            
            startKoin()
        
            let viewController = UIHostingController(rootView: ContentView())
            self.window = UIWindow(frame: UIScreen.main.bounds)
            self.window?.rootViewController = viewController
            self.window?.makeKeyAndVisible()
        
            return true
        }
}
