//
//  ViewController.swift
//  plist
//
//  Created by Eular on 15/4/21.
//  Copyright (c) 2015年 Eular. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        var arr: AnyObject = NSArray(contentsOfURL: NSBundle.mainBundle().URLForResource("arr", withExtension: "plist")!)!
        println(arr)
        
        var dict: AnyObject = NSDictionary(contentsOfURL: NSURL(fileURLWithPath:  NSBundle.mainBundle().pathForResource("data", ofType: "plist")!)!)!
        println(dict["user"])
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

