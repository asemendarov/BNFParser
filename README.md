# BNFParser
BNFParser — парсер дифференциальных уравнений описанных в форме Бэкуса — Наура (в нотации Корягина Сергея Викторовича)

#### Форма Бэкуса-Наура

    Language = "Programm" ProgramName Equations BeginConditions IntegrationConfitions
    ProgramName = Var
    Equations = "Equations" ":" Equation ... Equation
    BeginConditions = "BeginConditions" ":" BeginContition ... BeginCondition
    IntegrationConfitions = "IntegrationConfitions" ":"  IntegrationConfition

    Equation = IntegrationVar "=" RightBlock
    RightBlock = </ "-" /> AdditionBlock ("+" ! "-") ... AdditionBlock
    AdditionBlock = MultiplicationBlock ("*" ! "/") ... MultiplicationBlock 
    MultiplicationBlock = Var ! Number ! "(" RightBlock ")"

    BeginContition = Var "=" Number

    IntegrationConfition = IntegrationConfitionMethod IntegrationConfitionPeriod IntegrationConfitionStep
    IntegrationConfitionMethod = "method" "=" ("Euler" ! "Runge-Kutti-1" ! "Runge-Kutti-2" ! ... ! "Runge-Kutti-4")
    IntegrationConfitionPeriod = "t" "=" Number
    IntegrationConfitionStep = "dt" "=" Number

    IntegrationVar = Var "/dt"
    Var = Symbol ... Symbol
    Symbol = Character ! Number
    Number = FloatNumber ! IntNumber
    IntNumber = Digit ... Digit
    FloatNumber = IntNumber "." IntNumber
    Character = "a" ! "b" ! ... ! "z" ! SpecialCharacter
    SpecialCharacter = "_"
    Digit = 0 ! 1 ! ... ! 9

#### Текущее состояние
На данный момент программна находится в разработке...
