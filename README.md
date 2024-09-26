# Payments system

## Available actions:

An Administrator can create a User.\
An Administrator can block a Card due to exceeding the credit limit.

A User can create an Account.\
Account has 2 types - credit and debit. Credit account starts its life with bank's money.\
A User can top up balance of his Account.\
A User can view information about his Account - it's number, balance etc.\
A User can cancel an Account.

A User can create a Card. Every Card links to an Account.\
Card's type is fixed according to it Account's type.\
A User can view information about his Card - number, type and status.\
A User can make a Payment .\
A User can block his Card.

## Entities:

### User

#### • name

#### • surname

#### • email

### Account

#### • number

#### • minor_amount

#### • type

#### • currency

#### • status

#### • owner_id

### Card

#### • number

#### • type

#### • status

#### • account_id


### Payment

#### • id

#### • sender_account_id

#### • recipient_account_id

#### • amount

#### • description

#### • payment_date

#### • status

