//
//  main.m
//  JunkBird
//
//  Created by Matt Weaver on 2/13/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "Junk.h"

int main (int argc, const char * argv[])
{

  @autoreleasepool {
      
    NSLog(@"Starting!");
    
    [Junk notifyMeAsynchronouslyWithMessage:@"Hello world!"];
    
    Junk *someJunk = [[Junk alloc] init];
    
    for(int i = 0; i < 99; i++)
    {
      NSLog(@"adding");
      [Junk notifyMeAsynchronouslyWithMessage:[NSString stringWithFormat:@"Something %d", i]];
    }
    
    [someJunk printMyDatums];
      
  }
    return 0;
}

