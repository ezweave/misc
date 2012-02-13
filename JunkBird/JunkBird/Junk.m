//
//  Junk.m
//  JunkBird
//
//  Created by Matt Weaver on 2/13/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#import "Junk.h"

@interface Datum : NSObject {
@private
  NSInteger myCount;
}

- (void) setMyCount: (NSInteger) count_;
- (NSInteger) getMyCount;

+ (Datum*) initWithName: (NSString*) name andCount: (NSInteger) count;

@property (nonatomic, copy) NSString* name;

@end

@implementation Datum

@synthesize name;

- (void) setName:(NSString *)name_
{
  self.name = name_;
}

- (void) setMyCount: (NSInteger) count_
{
  self->myCount = count_;
}

- (NSInteger) getMyCount
{
  return self->myCount;
}

+ (Datum*) initWithName: (NSString*) name_ andCount: (NSInteger) count_
{
  Datum* myDatum = [[Datum alloc] init];
  myDatum.name = name_;
  myDatum->myCount = count_;
  
  return myDatum;
}

@end

@interface Junk ()

- (void) processNotification: (NSNotification*) notification;

@property (nonatomic) NSUInteger count;

@end

NSString* MY_MESSAGES = @"MY_MESSAGES";

@implementation Junk

@synthesize name, myDatums, count;

- (id)init {
  self = [super init];
  if (self) {
    [[NSNotificationCenter defaultCenter] addObserver:self 
                                             selector:@selector(processNotification:) 
                                                 name:MY_MESSAGES 
                                               object:nil];
  }
  return self;
}

- (void) processNotification: (NSNotification*) notification
{
  NSString *aMessage = (NSString*) [notification object];
  
  Datum *d = [Datum initWithName:aMessage andCount:self.count++];
  
  [self.myDatums insertValue:d inPropertyWithKey:[NSString stringWithFormat:@"MESSAGE: %lu", self.count]];
  
  NSLog(@"Added %@ with count of %lu", aMessage, self.count);
  
}

+ (void) notifyMeAsynchronouslyWithMessage: (NSString*) message
{
  NSNotification *myNotification = [NSNotification notificationWithName:MY_MESSAGES object:message];
  [[NSNotificationQueue defaultQueue] enqueueNotification:myNotification postingStyle:NSPostASAP];
}

@end


